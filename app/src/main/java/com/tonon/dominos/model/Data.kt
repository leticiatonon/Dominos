package com.tonon.dominosapp.model


import com.google.gson.annotations.SerializedName

data class category (
   val name: String
)

data class flavour(
    val image: Int,
    val name: String,
    val price: String,
    val id: Int,
)

data class ingredient (
    val image: Int,
    val name: String,
    val price: String,
    val id: Int
)

data class discount(
    val image: Int,
    val name: String,
    val description: String,
    val id: Int
)

data class food(

    @SerializedName("name") var name: String = "",
    @SerializedName("food") var flavour: List<flavour> = arrayListOf()
)




