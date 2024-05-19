package com.sjcoding.networksdk.ui.listScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.sjcoding.networksdk.domain.viewmodels.MoviesViewModel
import com.sjcoding.networksdk.data.model.Movie
import com.sjcoding.networksdk.data.repository.MoviesRepository
import com.sjcoding.networksdk.domain.viewmodels.MoviesViewModelFactory
import com.sjcoding.networksdk.ui.detailScreen.MovieDetailActivity
import com.sjcoding.networksdk.ui.theme.NetworkSDKTheme
import com.sjcoding.networksdk.ui.theme.lightGray
import kotlinx.coroutines.launch

class ListActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels {
        val repository = MoviesRepository()
        MoviesViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NetworkSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieApp()
                }
            }
        }
    }

    private fun handleMovieClick(movie: Movie) {
        gotoDetailScreen(movie)
    }

    private fun gotoDetailScreen(movie: Movie) {

        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie", movie)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        startActivity(intent)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun MovieApp() {
        val tabTitles = listOf("Popular", "Latest")
        val coroutineScope = rememberCoroutineScope()
        val pagerState = remember { PagerState() }

        Scaffold(
            topBar = {
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                TabContent(
                    pagerState = pagerState, tabTitles = tabTitles
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun TabContent(pagerState: PagerState, tabTitles: List<String>) {
        HorizontalPager(
            pageCount = tabTitles.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> PopularMoviesTab()
                1 -> LatestMoviesTab()
            }
        }
    }

    @Composable
    fun PopularMoviesTab() {
        val latestMovies by viewModel.popularMovies.collectAsState()
        MoviesTab(
            movies = latestMovies,
            onMovieClick = { handleMovieClick(it) }
        )
    }

    @Composable
    fun LatestMoviesTab() {
        val latestMovies by viewModel.latestMovies.collectAsState()
        MoviesTab(
            movies = latestMovies,
            onMovieClick = { handleMovieClick(it) }
        )
    }
}

