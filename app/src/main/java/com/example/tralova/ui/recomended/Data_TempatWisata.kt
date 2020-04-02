package com.example.tralova.ui.recomended

object Data_TempatWisata {
    private val namaTempat = arrayOf("Danau Toba","Nusa Penida","Taman Laut Bunaken","Wakatobi")

    private val desTempat = arrayOf(
        "Danau dengan keindahan yang tidak tertandingi ini merupakan danau vulkanik terbesar di Asia Tenggara dan terbesar kedua di dunia setelah Danau Victoria. Danau Toba sudah lama terkenal sebagai salah satu objek wisata Indonesia favorit yang sering dikunjungi sejak tahun 1980-an lho!",
        "Salah satu objek wisata di Bali yang populer di mata dunia saat ini adalah Nusa Penida. Tempat wisata Indonesia favorit ini adalah pilihan tepat untuk Toppers yang suka melakukan Island Hoping, dan menikmati keindahan bawah laut dengan snorkeling.",
        "Destinasi wisata populer di Indonesia lainnya yang juga ramai dikunjungi wisatawan asing adalah Taman Laut Bunaken yang tepatnya berada di Teluk Manado. Bunaken menjadi salah satu objek wisata di Indonesia yang mengundang decak kagum karena keindahan taman bawah lautnya yang sulit ditemukan di negara lain.",
        "Masih berada di Pulau Sulawesi, Taman Nasional Wakatobi juga merupakan salah satu tujuan wisata dunia bagi yang ingin mengeksplorasi keindahan alam bawah laut. Dengan luas mencapai 13.900 km2, tujuan wisata terkenal asal Indonesia ini memiliki tak kurang dari 112 jenis terumbu karang yang bersimbiosis dengan ikan-ikan bawah laut sehingga menciptakan panorama bawah laut yang tiada tanding."
    )

    private val kondisiTempat= arrayOf(
        "kawasan The Kaldera Nomadic Escape Danau Toba dipenuhi ribuan bikers peserta Suryanation Motorland Ridescape 2019. Tercatat 2.688 bikers dari 262 komunitas hadir dari Sabtu (25/10) pagi hingga Minggu (26/10) siang.",
        "Bupati Kabupaten Klungkung I Nyoman Suwirta menjelaskan dalam perlehatan kali ini, masih akan tetap mengkampanyekan keindahan bawah laut dan kebudayaan Nusa Penida.",
        "Saat syuting di Taman Nasional Bunaken, Manado, Sulawesi Utara, Jefri harus menjalani adegan diving. Meski belum pernah menyelam sebelumnya, pria 20 tahun ini bersikeras menjalani sendiri adegan tersebut tanpa bantuan pemeran pengganti.",
        "Gempa bumi tersebut terjadi di Kabupaten Wakatobi, Sulawesi Tenggara. Lindu menggetarkan dengan kekuatan magnitudo 5,5 dan kedalaman 10 kilometer."
    )

    private val photoTempat = arrayOf(
        "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2015/01/wisata-indonesia-1-Sindonews.jpg",
        "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2015/01/wisata-indonesia-2-Beautiful-Bromo.jpg",
        "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2015/01/wisata-indonesia-3-Twisata.jpg",
        "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2015/01/wisata-indonesia-4-Eco-Lodge-Tourism.jpg"
    )

    private val jrkTempat= arrayOf("5 Km","8Km","10Km","15Km")

    val listData: ArrayList<TempatWisata>
        get(){
            val list = arrayListOf<TempatWisata>()
            for (position in namaTempat.indices){
                val t4 = TempatWisata()
                t4.nameWisata = namaTempat[position]
                t4.DeskWisata = desTempat[position]
                t4.KondisiWisata = kondisiTempat[position]
                t4.jarakWisata = jrkTempat[position]
                t4.photoWisata = photoTempat[position]

                list.add(t4)
            }
            return list
        }
}