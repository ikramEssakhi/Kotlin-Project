package com.example.mykoltinapp

import android.os.Parcel
import android.os.Parcelable

// DeezerResponse.kt
data class DeezerResponse(val data: List<Track>)

// Track.kt
data class Track(
    val title: String,
    val artist: Artist,
    val album: Album,
    val duration: Int
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Track> = object : Parcelable.Creator<Track> {
            override fun createFromParcel(parcel: Parcel): Track {
                return Track(parcel)
            }

            override fun newArray(size: Int): Array<Track?> {
                return arrayOfNulls(size)
            }
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(Artist::class.java.classLoader) ?: Artist("", ""),
        parcel.readParcelable(Album::class.java.classLoader) ?: Album("", "", ""),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeParcelable(artist, flags)
        parcel.writeParcelable(album, flags)
        parcel.writeInt(duration)
    }

    override fun describeContents(): Int {
        return 0
    }
}

// Album.kt
data class Album(
    val title: String,
    val cover_medium: String,
    val cover_big: String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Album> = object : Parcelable.Creator<Album> {
            override fun createFromParcel(parcel: Parcel): Album {
                return Album(parcel)
            }

            override fun newArray(size: Int): Array<Album?> {
                return arrayOfNulls(size)
            }
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(cover_medium)
        parcel.writeString(cover_big)
    }

    override fun describeContents(): Int {
        return 0
    }
}

// Artist.kt
data class Artist(
    val name: String,
    val picture_big: String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Artist> = object : Parcelable.Creator<Artist> {
            override fun createFromParcel(parcel: Parcel): Artist {
                return Artist(parcel)
            }

            override fun newArray(size: Int): Array<Artist?> {
                return arrayOfNulls(size)
            }
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(picture_big)
    }

    override fun describeContents(): Int {
        return 0
    }
}
