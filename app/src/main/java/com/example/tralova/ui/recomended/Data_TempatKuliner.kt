package com.example.tralova.ui.recomended

object Data_TempatKuliner {
    private val namaTempatKul = arrayOf("Kedai Ucok Durian","Mie Sop Methodist","Martabak Piring Murni","Soto Keswan")

    private val desTempatKul = arrayOf(
        "Liburan ke Medan, tak lengkap jika belum mencoba durian di Kedai Ucok atau Pak Zainal Abidin. Kedainya terletak di Jalan KH Wahid Hasyim No 30-32 dan sudah terkenal di kalangan artis ibu kota hingga pejabat negara. Saking terkenalnya, Presiden Joko Widodo juga pernah mampir ke sini. Warung yang memiliki tagline ‘Belum ke Medan kalau tidak mampir ke Ucok Durian’ ini tak hanya menawarkan durian kupas, tapi juga pancake, durian beku, serta es krim durian. Rasanya nikmat dengan kualitas buah yang terbaik.",
        "Mie Sop ini juga dikenal dengan nama Bakso Methodist. Disebut demikian karena kuliner Medan ini dulunya berada di samping kampus Universitas Methodist Indonesia. Seporsi bakso kuliner legendaris Medan ini terdiri dari bola daging, mie kuning, bihun, kwetiau, kecambah, jeroan serta irisan daging. Belum lagi kaldunya yang bearoma sedap. Porsinya memang besar, tapi kamu bisa memesan setengah. Makin penasaran kan? Langsung saja datang ke Jalan T. Cik Ditiro.",
        "Sudah bosan dengan martabak biasa? Kamu wajib coba martabak piring yang cukup legendaris di Medan. Letaknya di Jalan Surabaya No. 39. Sesuai dengan namanya, camilan ini dicetak menggunakan piring di atas tungku arang. Kamu bisa memesan martabak tipis atau tebal dengan topping keju, meses, serta cokelat. Bungkusnya pun unik dengan menggunakan daun pisang. Soal rasa tak perlu diragukan lagi.",
        "Tjong A Fie Mansion merupakan salah satu objek wisata bersejarah di Medan. Saat berkunjung ke sini, mampirlah ke Warung Soto Kesawan yang berada tak jauh dari sini. Sesuai dengan namanya, menu yang ditawarkan di sini adalah soto dengan kuah gurih yang menggiurkan. Isian dari udang, paru kering, daging sapi serta ayam kampung memiliki tekstur empuk yang mudah dikunyah. Campuran rempah yang kuat membuat sajian ini pas disantap sore hari."
    )

    private val photoTempatKul = arrayOf(
        "https://media.travelingyuk.com/wp-content/uploads/2018/02/Kuliner-Medan-Ucok-Durian-Medan.jpg",
        "https://media.travelingyuk.com/wp-content/uploads/2018/02/Kuliner-Medan-Mie-Sop-Methodist.jpg",
        "https://media.travelingyuk.com/wp-content/uploads/2018/02/Kuliner-Medan-Martabak-Piring.jpg",
        "https://media.travelingyuk.com/wp-content/uploads/2018/02/Kuliner-Medan-Soto-Kesawan.jpg"
    )

    private val jrkTempatKul= arrayOf("5 Km","8Km","10Km","15Km")

    val listData: ArrayList<TempatKuliner>
        get(){
            val list = arrayListOf<TempatKuliner>()
            for (position in namaTempatKul.indices){
                val t4K = TempatKuliner()
                t4K.nameKuliner = namaTempatKul[position]
                t4K.DeskKuliner = desTempatKul[position]
                t4K.jarakKuliner = jrkTempatKul[position]
                t4K.photoKuliner = photoTempatKul[position]
                list.add(t4K)
            }
            return list
        }
}