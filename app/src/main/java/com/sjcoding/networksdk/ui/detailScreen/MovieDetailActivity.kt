package com.sjcoding.networksdk.ui.detailScreen

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sjcoding.networksdk.data.model.Movie
import com.sjcoding.networksdk.ui.ImageLoader
import com.sjcoding.networksdk.ui.theme.NetworkSDKTheme

class MovieDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra("movie", Movie::class.java)
        } else {
            intent?.getParcelableExtra("movie")
        }

        if(movie == null){
            finish()
        }

        setContent {
            NetworkSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieDetails(movie = movie!!)
                }
            }
        }
    }

    @Composable
    fun MovieDetails(movie: Movie) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ImageLoader(
                image = movie.posterPath ?: "",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = movie.title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "Release Date: ${movie.releaseDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = movie.overview ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
    }
}
