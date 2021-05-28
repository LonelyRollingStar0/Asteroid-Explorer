package services

import models.MyAsteroid
import retrofit2.Call
import retrofit2.http.GET

interface AsteroidService {

    @GET("mpc")
    fun getAffectedAsteroidList () : Call<List<MyAsteroid>>
}