package com.kelompok.projectsubmussion.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kelompok.projectsubmussion.page.PageBuah
import com.kelompok.projectsubmussion.page.PageSayur

class TabAdapter (manage : FragmentManager) : FragmentPagerAdapter(manage) {


    private val pages = listOf(
        PageBuah(),
        PageSayur()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "buah"
            else -> "sayur"
        }
    }
}