package com.EdwarDa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.EdwarDa.AppNavegation.AppNavegation
import com.EdwarDa.ui.theme.PracticaExamenTheme
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            val darkModeStore = StoreDarkMode(this)
            val darkMode = darkModeStore.getDarkMode.collectAsState(initial = false)
            PracticaExamenTheme(
                darkTheme = darkMode.value
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                   AppNavegation(
                       darkModeStore = darkModeStore,
                       darkMode = darkMode.value
                   )
                }
            }
        }
    }
}

@Composable
fun DarkMode(darkModeStore: StoreDarkMode, darkMode: Boolean){
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            if(darkMode){
                darkModeStore.saveDarkMode(false)
            }else{
                darkModeStore.saveDarkMode(true)
            }
        }
    }) {
        Text("Cambiar a Dark")
    }

    Switch(checked = darkMode, onCheckedChange = { isChecket ->
        scope.launch {
            darkModeStore.saveDarkMode(isChecket)
        }
    })
}
