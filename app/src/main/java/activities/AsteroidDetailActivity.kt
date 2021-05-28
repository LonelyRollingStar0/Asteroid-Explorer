package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapi.R
import kotlinx.android.synthetic.main.activity_asteroid_detail.*
import models.MyAsteroid

class AsteroidDetailActivity : AppCompatActivity() {

    val TAG = "AsteroidDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asteroid_detail)

        val asteroid = intent.getParcelableExtra<MyAsteroid>(EXTRA_ASTEROID)
        Log.d(TAG, "oNCreate: " + asteroid)


        if (asteroid != null) {
            asteroidName2.text = " " + asteroid.readable_des
            eccentricity.text = "Eccentricity: " + asteroid.e
            inclination.text = "Inclination: " + asteroid.i
            semimajorAxis.text = "Semi-Major Axis: " + asteroid.a

        }
    }

    companion object {
        val EXTRA_ASTEROID = "asteroid"
    }
}