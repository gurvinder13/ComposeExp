package com.example.composeexp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeexp.model.Result
import com.example.composeexp.utils.AppConstant.IMAGE_URL
import com.example.composeexp.viewModels.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieListScreen() {
    val movieViewModel: MovieViewModel = viewModel()
    val movieList: State<List<Result>?> = movieViewModel.movieList.collectAsState()
    val loader = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    // Show progress bar if data is still loading
    if (movieList.value == null || loader.value) {
        ShowProgressBar(show = true)
    } else {
        LazyColumn {
            items(movieList.value!!) { movie ->
                MovieItem(result = movie)
            }
        }
    }

    // Hide progress bar once data is loaded
    LaunchedEffect(movieList.value) {
        if (movieList.value != null && movieList.value!!.isNotEmpty()) {
            coroutineScope.launch {
                delay(2000L) // 3 seconds delay
                loader.value = false
            }
        }
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(result: Result) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        GlideImage(
            model = IMAGE_URL + result.profile_path,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.White, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = result.name,
            Modifier.padding(8.dp),
        )
    }
}

@Composable
fun ShowProgressBar(show: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (show) {
            CircularProgressIndicator()
        }
    }
}
