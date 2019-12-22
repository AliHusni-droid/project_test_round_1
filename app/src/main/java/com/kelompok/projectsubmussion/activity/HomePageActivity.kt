package com.kelompok.projectsubmussion.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kelompok.projectsubmussion.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_home_page.*
import android.view.Menu
import android.view.MenuItem
import com.kelompok.projectsubmussion.R


class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        vp_main.adapter = TabAdapter(supportFragmentManager)
        tl_main.setupWithViewPager(vp_main)

        supportActionBar?.title = "Fruit and Vegetable List Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(this, item.itemId)
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setMode(homePageActivity: HomePageActivity, selectedMode: Int) {
             when (selectedMode) {
                 R.id.menu_profile -> {
                    val intent = Intent(homePageActivity,ProfileActivity::class.java)
                     homePageActivity.startActivity(intent)
                 }
             }
         }
    }
}
