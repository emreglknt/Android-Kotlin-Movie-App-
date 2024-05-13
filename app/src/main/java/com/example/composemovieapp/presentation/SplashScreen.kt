package com.example.composemovieapp.presentation
import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composemovieapp.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController, context: MainActivity) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        delay(3000) // 4 saniye bekleniyor
        navController.popBackStack() // Geriye git
        navController.navigate(Screen.MovieScreen.route) // MovieScreen'e geç
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color(0xFF000059)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoaderAnimation(
            modifier = Modifier.size(500.dp), anim = R.raw.lottiesplashanim
        )

    }
}

@Composable
fun LoaderAnimation(modifier: Modifier, anim: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(anim))

    LottieAnimation(
        composition = composition, iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

private fun onBoardingIsFinished(context: MainActivity): Boolean {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)

}