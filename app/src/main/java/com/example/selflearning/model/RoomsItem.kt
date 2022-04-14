package com.example.selflearning


import com.example.selflearning.Utils.Companion.mydateFormat
import com.google.gson.annotations.SerializedName
import java.util.*

data class RoomsItem(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_occupied")
    val isOccupied: Boolean,
    @SerializedName("max_occupancy")
    val maxOccupancy: Int,
    @SerializedName("name")
    val name: String
) {

    fun getDate() :Date{
        return mydateFormat.parse(createdAt)
    }
}