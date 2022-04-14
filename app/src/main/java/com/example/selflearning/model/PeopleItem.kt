package com.example.selflearning


import com.google.gson.annotations.SerializedName

data class PeopleItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("favouriteColor")
    val favouriteColor: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("jobTitle")
    val jobTitle: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("phone")
    val phone: String
)