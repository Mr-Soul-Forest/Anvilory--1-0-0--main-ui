/**Copyright 2025 Forge-of-Ovorldule (https://github.com/Forge-of-Ovorldule) and Mr-Soul-Forest (https://github.com/Mr-Soul-Forest)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package forgeofovorldule.anvilory

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class SaveData(
    var app_version: Long = 0,
    var language: Languages = Languages.EN,
    var plots: MutableList<Plot> = mutableListOf(Plot())
)

expect object SaveStorage {
    fun load(): String?
    fun save(data: String)
}


object SaveManager {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
    }

    var data: SaveData = load()
        private set

    private fun load(): SaveData {
        val raw = SaveStorage.load()
            ?: return SaveData()

        return runCatching {
            json.decodeFromString<SaveData>(raw)
        }.getOrElse {
            SaveData()
        }
    }

    fun save() {
        SaveStorage.save(
            json.encodeToString(data)
        )
    }

    fun reset() {
        data = SaveData()
        save()
    }
}