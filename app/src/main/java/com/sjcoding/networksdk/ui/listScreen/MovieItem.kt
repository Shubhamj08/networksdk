package com.sjcoding.networksdk.ui.listScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sjcoding.networksdk.data.model.Movie
import com.sjcoding.networksdk.ui.ImageLoader
import com.sjcoding.networksdk.ui.theme.lightGray

private const val IMAGE_PATH = "https://image.tmdb.org/t/p/w185"
@Composable
fun MovieItem(movie: Movie, onClick: (movie: Movie) -> Unit = {}) {
    // UI for displaying individual movie item
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(lightGray)
            .aspectRatio(0.75f)
            .clickable(onClick = { onClick(movie) })
    ) {
        ImageLoader(
            image = movie.posterPath ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}