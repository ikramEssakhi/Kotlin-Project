// TrackDetailsActivity.kt
package com.example.mykoltinapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class TrackDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_details)

        val bigCoverImageView: ImageView = findViewById(R.id.bigCoverImageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val artistTextView: TextView = findViewById(R.id.artistTextView)
        val durationTextView: TextView = findViewById(R.id.durationTextView)
        val artistImageView: ImageView = findViewById(R.id.artistImageView)
        val albumTitleTextView: TextView = findViewById(R.id.albumTitleTextView)

        // Retrieve track details from Intent
        val track = intent.getParcelableExtra<Track>("track")

        // Load and display track details
        track?.let {
            Glide.with(this)
                .load(it.album.cover_big)
                .into(bigCoverImageView)

            titleTextView.text = it.title
            artistTextView.text = it.artist.name
            durationTextView.text = "Duration: ${it.duration} seconds"

            // Load and display artist picture
            Glide.with(this)
                .load(it.artist.picture_big)
                .into(artistImageView)

            albumTitleTextView.text = "Album: ${it.album.title}"
        }
    }
}
