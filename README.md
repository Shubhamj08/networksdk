# NetworkSDK

NetworkSDK is a library for simplifying network operations in Android applications.

## Setup

### 1. Add JitPack Repository

Add the following lines to your `settings.gradle.kts` file:

```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
```

### 2. Add dependency

Add the following line to your `build.gradle.kts(Module Level)` file:

```kotlin
implementation("com.github.Shubhamj08:networksdk:1.0.11")
```

### 3. Initialize NetworkSDK

Initialize the NetworkSDK with the base URL of your API:

```kotlin
NetworkSDK.initialize(BASE_URL)
```

### 3. Access Repository

Access the repository using `NetworkModule.networkRepository`, which is of type `NetworkRepository`:

```kotlin
val repository = NetworkModule.networkRepository
```

## Usage

The responses are wrapped in a sealed class `Result`, which can be either `Success` or `Error`.

```kotlin
sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}
```

The repository provides several methods for making network requests.

`getData`
```kotlin
fun getData(
    endpoint: String,
    responseType: Type?,
    headers: Map<String, String>?
): Result<T>
```



`postData`
```kotlin
fun postData(
    endpoint: String,
    body: Any,
    responseType: Type?,
    headers: Map<String, String>?
): Result<T>
```



`putData`
```kotlin
fun putData(
    endpoint: String,
    body: Any,
    responseType: Type?,
    headers: Map<String, String>?
): Result<T>
```



`patchData`
```kotlin
fun patchData(
    endpoint: String,
    body: Any,
    responseType: Type?,
    headers: Map<String, String>?
): Result<T>
```



`deleteData`
```kotlin
fun deleteData(
    endpoint: String,
    responseType: Type?,
    headers: Map<String, String>?
): Result<T>
```


## Example

```kotlin
val repository = NetworkModule.networkRepository

val result = repository.getData(
    endpoint = "endpoint",
    responseType = Example::class.java,
    headers = mapOf("Authorization" to "Bearer YOUR_TOKEN")
)

when (result) {
    is Result.Success -> {
        val data = result.data // handle successful response
    }
    is Result.Error -> {
        val exception = result.exception // handle error
    }
}
```

