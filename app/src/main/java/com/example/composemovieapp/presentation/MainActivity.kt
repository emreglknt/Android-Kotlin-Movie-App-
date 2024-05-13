package com.example.composemovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemovieapp.presentation.movie_detail.MovieDetailScreen
import com.example.composemovieapp.presentation.movies.MovieScreen
import com.example.composemovieapp.ui.theme.ComposeMovieAppTheme
import com.example.composemovieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route ){


                        composable(route = Screen.SplashScreen.route){
                            SplashScreen(navController = navController,context = this@MainActivity)
                        }


                        composable(route = Screen.MovieScreen.route){
                            MovieScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}"){
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}



