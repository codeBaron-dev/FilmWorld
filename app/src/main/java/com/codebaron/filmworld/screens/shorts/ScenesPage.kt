package com.codebaron.filmworld.screens.shorts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.codebaron.filmworld.screens.home.HomeActivity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScenesPage(
    mainActivity: HomeActivity,
    networkState: Boolean,
    navHostController: NavHostController
){
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black)
        ){}
        Text(text = "Scenes Page")
    }
}