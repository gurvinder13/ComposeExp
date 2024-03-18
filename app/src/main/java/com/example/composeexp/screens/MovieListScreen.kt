package com.example.composeexp.screens

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeexp.R
import com.example.composeexp.model.MovieListResponse
import com.example.composeexp.model.Result
import com.example.composeexp.viewModels.MovieViewModel

@Composable
fun MovieListScreen() {
val movieViewModel : MovieViewModel = viewModel()
    val movieList: State<List<Result>> = movieViewModel.movieList.collectAsState()
    LazyColumn( ){
        Log.d("data",movieList.value.toString())
        item(movieList.value) {
            if (movieList.value.isNotEmpty()){
                movieList.value.forEach {
                    MovieItem(result = it)
                }

            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(result: Result) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        GlideImage(
            model = "https://image.tmdb.org/t/p/w500/"+ result.profile_path,
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
