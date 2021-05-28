package com.example.myapi

import activities.AsteroidDetailActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.media.MediaPlayer
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import helpers.AsteroidsAdapter
import models.MyAsteroid
import services.AsteroidService
import services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sortAsteroidsByName()

        sortDate.setOnClickListener {

            sortAsteroidsByDate()
        }

        sortName.setOnClickListener {

            sortAsteroidsByName()
        }

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.katamaristars)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()


    }

    private fun sortAsteroidsByDate() {
            var asteroid = intent.getParcelableExtra<MyAsteroid>(AsteroidDetailActivity.EXTRA_ASTEROID)


            val destinationService  = ServiceBuilder.buildService(AsteroidService::class.java)
            val requestCall =destinationService.getAffectedAsteroidList()
            //make network call asynchronously
            requestCall.enqueue(object : Callback<List<MyAsteroid>>{
                override fun onResponse(call: Call<List<MyAsteroid>>, response: Response<List<MyAsteroid>>) {
                    Log.d("Response", "onResponse: ${response.body()}")
                    if (response.isSuccessful){
                        var asteroidList  = response.body()!!
                        asteroidList = asteroidList.toMutableList()
                        Log.d("Response", "asteroidslist size : ${asteroidList.size}")
                        asteroid_recycler.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                            asteroidList.sortBy { MyAsteroid -> MyAsteroid.last_obs }
                            adapter = AsteroidsAdapter(asteroidList)
                        }  }else{
                        Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<MyAsteroid>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Something else went wrong $t", Toast.LENGTH_SHORT).show()
                }
            })

        }

    private fun sortAsteroidsByName() {
        var asteroid = intent.getParcelableExtra<MyAsteroid>(AsteroidDetailActivity.EXTRA_ASTEROID)


        val destinationService  = ServiceBuilder.buildService(AsteroidService::class.java)
        val requestCall =destinationService.getAffectedAsteroidList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<MyAsteroid>>{
            override fun onResponse(call: Call<List<MyAsteroid>>, response: Response<List<MyAsteroid>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    var asteroidList  = response.body()!!
                    asteroidList = asteroidList.toMutableList()
                    Log.d("Response", "asteroidslist size : ${asteroidList.size}")
                    asteroid_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = AsteroidsAdapter(asteroidList)
                    }  }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<MyAsteroid>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something else went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun playSound(view: View) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.katamaristars)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }


    fun pauseSound(view: View) {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) mMediaPlayer!!.pause()
    }


    // 4. Closes the MediaPlayer when the app is closed
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }


}


