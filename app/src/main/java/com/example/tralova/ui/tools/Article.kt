package com.example.tralova.ui.tools

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    var Judul:String ="",
    var DeskArt:String = "",
    var Sumber:String ="",
    var Img_Art: Uri = Uri.parse("")
): Parcelable

