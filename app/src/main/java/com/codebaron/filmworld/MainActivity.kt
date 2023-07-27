package com.codebaron.filmworld

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebaron.filmworld.navigations.NavigationManager
import com.codebaron.filmworld.ui.theme.FilmWorldTheme
import com.codebaron.sharedlogc.internet.InternetConfigurationViewModel
import com.codebaron.sharedlogc.internet.InternetStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
                    CheckNetworkState(this)
                }
            }
        }
    }
}

@Composable
fun CheckNetworkState(mainActivity: MainActivity) {
    val isNetworkAvailable = remember { mutableStateOf(false) }
    val internetConnectionViewModel = hiltViewModel<InternetConfigurationViewModel>()
    val state by internetConnectionViewModel.networkStates(mainActivity)!!.observeAsState()
    when (state) {
        is InternetStates.IsInternetAvailable -> {
            isNetworkAvailable.value = true
        }
        is InternetStates.HasInternetStateChanged -> {
            isNetworkAvailable.value = true
        }
        is InternetStates.IsInternetConnectionLost -> {
            isNetworkAvailable.value = false
            Toast.makeText(mainActivity, "Internet disconnected", Toast.LENGTH_SHORT).show()
        }
    }
    NavigationManager(mainActivity = mainActivity, networkState = isNetworkAvailable.value)
}