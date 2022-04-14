package com.example.selflearning.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.selflearning.PeopleItem
import com.example.selflearning.databinding.PeopleListLayoutBinding
import com.example.selflearning.network.RoomApi
import com.squareup.picasso.Picasso

class PeopleViewHolder(val binding: PeopleListLayoutBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
    fun onBind(people: PeopleItem){
        binding.peopleListName.text = people.firstName +" " + people.lastName
        binding.peopleListEmail.text = people.email
        binding.peopleListJobTitle.text = people.jobTitle

        Picasso
            .get()
            .load(RoomApi.IMAGE_URL+people.avatar[0]+".jpg")
            //.resize(50,50)
            .into(binding.peopleListImage)

    }
}