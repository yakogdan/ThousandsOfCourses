package com.yakogdan.thousandsofcourses.data.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(

    @SerialName("hasLike")
    val hasLike: Boolean,

    @SerialName("id")
    val id: Int,

    @SerialName("price")
    val price: String,

    @SerialName("publishDate")
    val publishDate: String,

    @SerialName("rate")
    val rate: String,

    @SerialName("startDate")
    val startDate: String,

    @SerialName("text")
    val text: String,

    @SerialName("title")
    val title: String,
)