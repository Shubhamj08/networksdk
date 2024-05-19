package com.sjcoding.networksdk.ui.listScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.sjcoding.networksdk.data.model.Movie

@Composable
fun MoviesTab(
    movies: List<Movie>,
    onMovieClick: (movie: Movie) -> Unit = {}
) {

    movies.let { movies ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onMovieClick(it) }
                )
            }
        }
    }
}