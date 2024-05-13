package com.example.composemovieapp.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.composemovieapp.domain.model.MovieModel
import com.example.composemovieapp.presentation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
                    .padding(10.dp) // Apply padding to the entire content area
            ) {
                Column {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        hint = "Batman",
                        onSearch = {
                            viewModel.onEvent(MovieEvent.Search(it))
                        }
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        content = {
                            items(state.movies) { movie ->
                                MovieRowItem(movie = movie, onItemClick = {
                                    navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                                })
                            }
                        }
                    )
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

                //loading statinde barı göster
                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }



            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Batman",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(
            value = text,
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text)
            }),
            onValueChange = {
                text = it
                onSearch(text)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieRowItem(movie: MovieModel, onItemClick: (MovieModel) -> Unit) {

    Card(
        Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                onItemClick(movie)
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = rememberImagePainter(data = movie.Poster),
                contentDescription = movie.Title,
                modifier = Modifier
                    .fillMaxSize()
                    .size(200.dp, 230.dp)
                    .clip(RoundedCornerShape(13.dp)), contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray.copy(.7f))
                    .padding(5.dp)
            ) {
                Text(
                    text = movie.Title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.align(Alignment.End)) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "")
                    Text(
                        text = movie.Year,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


        }

    }


}


@Preview
@Composable
fun MovieScreenPreview() {
    MovieScreen(navController = rememberNavController())
}