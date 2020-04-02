package com.example.tralova.ui.gallery

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//membuat class menjadi objek parcelable, agar dapat mengirimkan data secara Objek,
// tidak satu2 biji tipe data String, int, lagi. Tetapi langsung semua dengan class objek tersebut
@Parcelize
data class HealthCare(
    var pp: String="",
    var name: String = "",
    var Detail: String = "",
    var Obat: String = "",
    var umur: String = "",
    var asal: String = "",
    var Fungsi: String = ""
):Parcelable
