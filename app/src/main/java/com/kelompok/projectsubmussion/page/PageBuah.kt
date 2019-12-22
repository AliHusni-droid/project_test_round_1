package com.kelompok.projectsubmussion.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelompok.projectsubmussion.adapter.BuahAdapter
import com.kelompok.projectsubmussion.model.Buah

import com.kelompok.projectsubmussion.R

/**
 * A simple [Fragment] subclass.
 */
class PageBuah : Fragment() {

    private val itemsfruit : MutableList<Buah> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_page_buah, container, false)

        val list = view.findViewById<RecyclerView>(R.id.rv_fruit)
        dataFruit()
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = BuahAdapter(view.context, itemsfruit)
        return view
    }

    private fun dataFruit(){
        val name = resources.getStringArray(R.array.name_fruit)
        val img = resources.obtainTypedArray(R.array.picture_fruit)
        val description = resources.getStringArray(R.array.description_fruit)

        itemsfruit.clear()
        for (i in name.indices){
            itemsfruit.add(
                Buah(
                    name[i],
                    description[i],
                    img.getResourceId(i,0)
                )
            )
        }
        img.recycle()
    }
}