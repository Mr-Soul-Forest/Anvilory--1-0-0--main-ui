package forgeofovorldule.anvilory

import kotlinx.browser.window

actual object SaveStorage {

    private const val KEY = "save"

    actual fun load(): String? {
        return window.localStorage.getItem(KEY)
    }

    actual fun save(data: String) {
        window.localStorage.setItem(KEY, data)
    }
}