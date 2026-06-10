package forgeofovorldule.anvilory

import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = {
            if (loadingIsGood)
                SaveManager.save()
            exitApplication()
        },
        title = "Anvilory",
        icon = painterResource("app_icon.png")
    ) {
        val viewModel = remember { AppViewModel() }
        App(viewModel)
    }
}