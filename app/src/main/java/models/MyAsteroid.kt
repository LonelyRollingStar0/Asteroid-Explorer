package models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyAsteroid(
    val G: Double,
    val H: Double,
    val M: Double,
    val U: String,
    val a: Double,
    val comp: String,
    val d: Double,
    val des: String,
    val e: Double,
    val epoch: String,
    val flags: String,
    val i: Double,
    val last_obs: String,
    val num_obs: Int,
    val num_opp: Int,
    val om: Double,
    val pert_c: String,
    val pert_p: String,
    val readable_des: String,
    val ref: String,
    val rms: Double,
    val w: Double
) : Parcelable {
    
}