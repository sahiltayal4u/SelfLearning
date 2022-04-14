package com.example.selflearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.selflearning.adapter.TabAdapter
import com.example.selflearning.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var tabTitle = arrayOf("People", "Room")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pager = binding.tabDisplay
        var tl = binding.tabContent
        pager.adapter= TabAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tl, pager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}