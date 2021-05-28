package helpers

import activities.AsteroidDetailActivity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapi.R
import models.MyAsteroid
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import helpers.AsteroidsAdapter
import services.AsteroidService
import services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AsteroidsAdapter(private val asteroidsList: List<MyAsteroid>) :RecyclerView.Adapter<AsteroidsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.asteroids_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return asteroidsList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${asteroidsList.size} ")


        return holder.bind(asteroidsList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var asteroidName = itemView.findViewById<TextView>(R.id.asteroidName)
        var lastObserved = itemView.findViewById<TextView>(R.id.lastObserved)
        fun bind(asteroid: MyAsteroid) {

            asteroidName.text = asteroid.readable_des
            lastObserved.text = asteroid.last_obs
            itemView.setOnClickListener {
                val specificAsteroidIntent = Intent(itemView.context, AsteroidDetailActivity::class.java)
                specificAsteroidIntent.putExtra(AsteroidDetailActivity.EXTRA_ASTEROID, asteroid)
                itemView.context.startActivity(specificAsteroidIntent)
            }
        }

    }

}