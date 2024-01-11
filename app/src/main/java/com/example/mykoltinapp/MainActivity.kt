package com.example.mykoltinapp

// MainActivity.kt
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

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = TrackAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener { track ->
            //log the track title for now
            Log.d("MainActivity", "View Details clicked for ${track.title}")
        }
        // Make API call
        fetchData()


    }

    // MainActivity.kt
    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getTopTracks()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val tracks = response.body()?.data ?: emptyList()
                    adapter = TrackAdapter(tracks)
                    recyclerView.adapter = adapter
                } else {
                    // Handle error
                    Log.e("API Error", response.message())
                }
            }
        }
    }

}
