package com.sjcoding.networksdk.data.repository

import com.sjcoding.networksdk.BuildConfig
import com.sjcoding.networksdk.util.Result
import com.sjcoding.networksdk.NetworkSDK
import com.sjcoding.networksdk.data.model.Movie
import com.sjcoding.networksdk.data.model.MovieResponse
import com.sjcoding.networksdk.di.NetworkModule
import com.sjcoding.networksdk.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository {

    private val customHeaders = mapOf(
        "Authorization" to "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}",
        "accept" to "application/json"
    )

    private lateinit var commentsRepository: NetworkRepository

    init {
        initializeNetworkSdk()
    }

    private fun initializeNetworkSdk() {
        NetworkSDK.initialize("https://api.themoviedb.org/")
        commentsRepository = NetworkModule.networkRepository
    }

    suspend fun fetchPopularMovies(): List<Movie> {
        return fetchMovies("3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc")
    }

    suspend fun fetchLatestMovies(): List<Movie> {
        return fetchMovies("3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=primary_release_date.desc")
    }

    private suspend fun fetchMovies(endpoint: String): List<Movie> {
        return withContext(Dispatchers.IO) {

            when (val result = commentsRepository.getData<MovieResponse>(
                endpoint,
                MovieResponse::class.java,
                customHeaders
            )) {
                is Result.Success -> result.data.results
                is Result.Error -> {
                    emptyList()
                }
            }
        }
    }

}