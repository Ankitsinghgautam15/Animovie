package com.example.animovie.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Icon
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.animovie.data.model.Data
import com.example.animovie.data.model.MovieResponse
import com.example.animovie.domain.MovieListViewModel
import com.example.animovie.domain.ScreenState

@Composable
fun MovieScreen(navHostController: NavHostController){
    val viewModel: MovieListViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                when (val s = state.value) {
                    is ScreenState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is ScreenState.Success -> {
                        val movies = s.movies
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent),
                            content = {
                                items(movies.body()?.data?.size ?: 0) {
                                    ItemUI(movieList = movies.body()?.data ?: emptyList(), index = it, navHostController = navHostController)
                                }
                            }
                        )
                    }
                    is ScreenState.Error -> {
                        Text(
                            text = s.message,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    is ScreenState.Empty -> {
                        Text(
                            text = "No data",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ItemUI(movieList: List<Data>, index: Int, navHostController: NavHostController){
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                navHostController.navigate("Movie screen")
            },
            elevation = CardDefaults.cardElevation(
                8.dp
            )
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(
                model =  movieList[index].poster,
                contentDescription = "",
                modifier = Modifier.matchParentSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = movieList[index].title,
                modifier = Modifier
                    .fillMaxWidth()
                    .basicMarquee(),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 2f,
                        offset = Offset(0f, 2f)
                    )
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                )
                Text(
                    text = movieList[index].imdb_rating.toString(),
                    modifier = Modifier.padding(start = 4.dp)
                        .fillMaxWidth(),

                        color = Color.Black,
                        fontSize = 12.sp,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,


                )



            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = "Movie List") }
    )
}