package com.kelompok.projectsubmussion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_detail_sayur.*

class DetailSayurActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_detail_sayur)

        val name : String? = intent.getStringExtra("txt_detail_vegetable")
        val description : String? = intent.getStringExtra("txt_detail_desc_vegetable")

        tv_detail_vegetable.text = name
        tv_detail_description_vegetable.text = description
        iv_detail_vegetable.setImageResource(intent.getIntExtra("img__detail_vegetable",0))

        supportActionBar?.title = "Detail Page Vegetable"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}