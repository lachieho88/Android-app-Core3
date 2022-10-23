package au.edu.swin.sdmd.core3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Medals(val country: String,
             val ICO_code: String,
             val time_competed: Int,
             val gold: Int,
             val silver:Int ,
             val bronze:Int ,
             val total_medals: Int = gold + silver + bronze )
    : Parcelable {
}