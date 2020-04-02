package com.example.tralova.ui.share

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Group(
    var GroupName: String = "",
    var GroupPhoto: Uri = Uri.parse("")
):Parcelable