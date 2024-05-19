package com.sjcoding.networksdk.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

private const val IMAGE_PATH = "https://image.tmdb.org/t/p/w185"
@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    image: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
){
    Image(
        painter = rememberImagePainter(data = "$IMAGE_PATH/${image}"),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}