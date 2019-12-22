package com.kelompok.projectsubmussion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_detail_buah.*

class DetailBuahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_detail_buah)

        val name : String? = intent.getStringExtra("txt_detail_fruit")
        val description : String? = intent.getStringExtra("txt_detail_desc_fruit")

        tv_detail_fruit.text = name
        tv_detail_description_fruit.text = description
        iv_detail_fruit.setImageResource(intent.getIntExtra("img__detail_fruit",0))

        supportActionBar?.title = "Detail Page Fruit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}