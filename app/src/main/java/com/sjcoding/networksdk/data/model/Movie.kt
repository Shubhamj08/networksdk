package com.sjcoding.networksdk.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Double,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Double,
    @SerializedName("total_results") val totalResults: Double
)

data class Movie(
    val adult: Boolean? = null,
    val genreIds: List<Double>? = null,
    val id: Double? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val title: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Double? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.createDoubleArray()?.toList(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult == true) 1 else 0)
        parcel.writeDoubleArray(genreIds?.toDoubleArray())
        id?.let { parcel.writeValue(it) }
        parcel.writeString(overview)
        popularity?.let { parcel.writeValue(it) }
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        voteAverage?.let { parcel.writeValue(it) }
        voteCount?.let { parcel.writeValue(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
