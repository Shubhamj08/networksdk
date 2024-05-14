package com.sjcoding.networksdk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.sjcoding.networksdk.di.NetworkModule
import com.sjcoding.networksdk.util.Result
import com.sjcoding.networksdk.ui.theme.NetworkSDKTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkSDK.initialize("https://jsonplaceholder.typicode.com/")

        lifecycleScope.launch {
            val commentsRepository = NetworkModule.networkRepository

            val customHeaders = mapOf("Authorization" to "Bearer token123")

            when(val result = commentsRepository.getData<List<Comment>>("comments", customHeaders)){
                is Result.Success -> {
                    Log.d("MainActivity", "GET Data received: ${result.data}")
                }
                is Result.Error -> {
                    Log.d("MainActivity", "GET request failed: ${result.exception.message}")
                }
            }

        }

        setContent {
            NetworkSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetworkSDKTheme {
        Greeting("Android")
    }
}