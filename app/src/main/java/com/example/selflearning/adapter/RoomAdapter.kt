package com.example.selflearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.selflearning.RoomsItem
import com.example.selflearning.databinding.RoomListLayoutBinding


class RoomAdapter: RecyclerView.Adapter<RoomViewHolder> {

    private lateinit var roomlists: List<RoomsItem>

    constructor(roomsItem: List<RoomsItem>):super(){
        this.roomlists = roomsItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RoomViewHolder (
        RoomListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ),parent.context
    )

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.onBind(roomlists[position])
    }

    override fun getItemCount(): Int {
        return roomlists.size
    }

}