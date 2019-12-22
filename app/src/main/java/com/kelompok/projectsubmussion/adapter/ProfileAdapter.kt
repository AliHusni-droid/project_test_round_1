package com.kelompok.projectsubmussion.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kelompok.projectsubmussion.page.PageProfileAli
import com.kelompok.projectsubmussion.page.PageProfileBayu
import com.kelompok.projectsubmussion.page.PageProfileAlwi

class ProfileAdapter (manage : FragmentManager) : FragmentPagerAdapter(manage) {

    private val pages = listOf(
        PageProfileAlwi(),
        PageProfileAli(),
        PageProfileBayu()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "alwi"
            1 -> "ali"
            else -> "bayu"
        }
    }
}