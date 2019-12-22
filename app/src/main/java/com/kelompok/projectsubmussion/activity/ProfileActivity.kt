package com.kelompok.projectsubmussion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.kelompok.projectsubmussion.R
import com.kelompok.projectsubmussion.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_profile)

        vp_profile.adapter = ProfileAdapter(supportFragmentManager)
        tl_profile.setupWithViewPager(vp_profile)

        supportActionBar?.title = "Page Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
