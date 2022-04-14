package com.example.selflearning.adapter

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.selflearning.R
import com.example.selflearning.RoomsItem
import com.example.selflearning.Utils.Companion.dateFormat
import com.example.selflearning.databinding.RoomListLayoutBinding


class RoomViewHolder(val binding: RoomListLayoutBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
    fun onBind(room: RoomsItem){
        binding.roomListName.text = room.name
        binding.roomListCreateDate.text = dateFormat.format(room.getDate())
        binding.roomListOccupancyCount.text = room.maxOccupancy.toString()
        //binding.roomListStatus.text = room.isOccupied.toString()

        val roomStatus = room.isOccupied
        if(roomStatus == true){
            binding.roomListStatus.text = "Occupied"
            binding.roomListStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
        }
        else{
            binding.roomListStatus.text = "Not Occupied"
            binding.roomListStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}