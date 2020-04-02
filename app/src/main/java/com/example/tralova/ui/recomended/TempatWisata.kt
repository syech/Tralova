package com.example.tralova.ui.recomended

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempatWisata(
    var photoWisata:String="",
    var nameWisata: String = "",
    var DeskWisata:String="",
    var KondisiWisata:String="",
    var jarakWisata:String=""
):Parcelable

