package com.example.tralova.ui.recomended

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempatKuliner(
    var photoKuliner:String="",
    var nameKuliner: String = "",
    var DeskKuliner:String="",
    var jarakKuliner:String=""
):Parcelable