package com.example.mykoltinapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Top100Activity.kt
class Top100Activity : AppCompatActivity() {

    private lateinit var recyclerViewTop100: RecyclerView
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_100)

        recyclerViewTop100 = findViewById(R.id.recyclerViewTop100)
        adapter = TrackAdapter(emptyList())

        recyclerViewTop100.layoutManager = LinearLayoutManager(this)
        recyclerViewTop100.adapter = adapter

        fetchTop100Tracks()
    }

    private fun fetchTop100Tracks() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getTop100Tracks()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val tracks = response.body()?.data ?: emptyList()
                    adapter = TrackAdapter(tracks)
                    recyclerViewTop100.adapter = adapter
                } else {
                    // Handle error
                    Log.e("API Error", response.message())
                }
            }
        }
    }
}
