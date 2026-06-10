package forgeofovorldule.anvilory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate() {
        enableEdgeToEdge()
        super.onCreate()
        AppContext.context = this

        setContent {
            val viewModel = remember { AppViewModel() }

            App(viewModel)
        }
    }

    override fun onStop() {
        super.onStop()
        if (loadingIsGood)
            SaveManager.save()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = remember { AppViewModel() }

    App(viewModel)
}