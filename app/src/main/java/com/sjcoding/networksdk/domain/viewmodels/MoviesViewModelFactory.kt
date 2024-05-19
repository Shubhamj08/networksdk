package com.sjcoding.networksdk.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sjcoding.networksdk.data.repository.MoviesRepository

class MoviesViewModelFactory(private val repository: MoviesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesViewModel::class.java)){

            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}