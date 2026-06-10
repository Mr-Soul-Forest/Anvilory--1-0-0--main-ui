package forgeofovorldule.anvilory

import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM

actual object SaveStorage {

    private val file = "save.json".toPath()

    actual fun load(): String? {
        return runCatching {
            FileSystem.SYSTEM.read(file) {
                readUtf8()
            }
        }.getOrNull()
    }

    actual fun save(data: String) {
        FileSystem.SYSTEM.write(file) {
            writeUtf8(data)
        }
    }
}