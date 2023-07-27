package com.codebaron.filmworld.screens.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebaron.filmworld.navigations.BottomNav
import com.codebaron.filmworld.screens.home.ui.theme.FilmWorldTheme
import com.codebaron.sharedlogc.internet.InternetConfigurationViewModel
import com.codebaron.sharedlogc.internet.InternetStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    CheckNetworkStatus(this)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CheckNetworkStatus(homeActivity: HomeActivity) {
    val isNetworkAvailable = remember { mutableStateOf(false) }
    val internetConnectionViewModel = hiltViewModel<InternetConfigurationViewModel>()
    val state by internetConnectionViewModel.networkStates(homeActivity)!!.observeAsState()
    when (state) {
        is InternetStates.IsInternetAvailable -> {
            isNetworkAvailable.value = true
        }
        is InternetStates.HasInternetStateChanged -> {
            isNetworkAvailable.value = true
        }
        is InternetStates.IsInternetConnectionLost -> {
            isNetworkAvailable.value = false
            Toast.makeText(homeActivity, "Internet disconnected", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        bottomBar = { BottomNav(homeActivity, isNetworkAvailable.value) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ) {}
    }
}