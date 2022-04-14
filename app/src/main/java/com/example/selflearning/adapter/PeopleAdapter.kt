package com.example.selflearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.selflearning.PeopleItem
import com.example.selflearning.databinding.PeopleListLayoutBinding


class PeopleAdapter : RecyclerView.Adapter<PeopleViewHolder> {

    private lateinit var peoplelists : List<PeopleItem>

    constructor(peoplesItem: List<PeopleItem>): super(){
        this.peoplelists = peoplesItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PeopleViewHolder (
        PeopleListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ),parent.context
    )

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.onBind(peoplelists[position])
    }

    override fun getItemCount(): Int {
        return peoplelists.size
    }
}