package com.example.composemovieapp.presentation.movie_detail

import android.icu.text.CaseMap.Title
import android.text.style.TtsSpan.TextBuilder
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.composemovieapp.domain.model.MovieDetailModel
import com.example.composemovieapp.presentation.Screen
import com.example.composemovieapp.presentation.movies.MoviesViewModel


@Composable
fun MovieDetailScreen(viewModel: MovieDetailsViewModel = hiltViewModel())
{
    val state = viewModel.state.value
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter)
    {
        state.movie?.let {movie->
            BackPoster(movie=movie)
            ForegroundPoster(movie=movie)

            Column (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
                    .align(
                        Alignment.BottomCenter
                    ).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            )
            {

                Text(
                    text = movie.Title.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                movie.Runtime?.let { TextBuilder(icon=Icons.Filled.Warning,title="Total Time",bodyText= it) }
                movie.Actors?.let { TextBuilder(Icons.Filled.Person,title="Actors",bodyText= it) }
                movie.Plot?.let { TextBuilder(icon=Icons.Filled.Info,title="Movie Summary",bodyText= it) }





            }


        }

        if (state.error.isNotBlank()) {
            Text(text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        
    }


}


@Composable
fun BackPoster(movie: MovieDetailModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Image(
            painter = rememberImagePainter(data = movie.Poster),
            contentDescription = movie.Title,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.6f),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.DarkGray
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }

}

@Composable
fun ForegroundPoster(movie: MovieDetailModel) {

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 80.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = rememberImagePainter(data = movie.Poster),
            contentDescription = movie.Title,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .aspectRatio(6f / 8f), // 16:9 en boy oranı
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
}


@Composable
fun TextBuilder(icon:ImageVector,title: String,bodyText:String){
    Row {
        Icon(
            imageVector = icon,
            contentDescription ="Person",
            tint = Color.White)

        Text(
            text = title,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}
