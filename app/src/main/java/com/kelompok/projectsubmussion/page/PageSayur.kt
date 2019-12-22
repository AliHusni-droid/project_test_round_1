package com.kelompok.projectsubmussion.page


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kelompok.projectsubmussion.R
import com.kelompok.projectsubmussion.adapter.SayurAdapter
import com.kelompok.projectsubmussion.model.Sayur

/**
 * A simple [Fragment] subclass.
 */
class PageSayur : Fragment() {

    private val itemsvegetable : MutableList<Sayur> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_page_sayur, container, false)

        val list = view.findViewById<RecyclerView>(R.id.rv_vegetable)
        dataVegetable()
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = SayurAdapter(view.context, itemsvegetable)
        return view
    }
    private fun dataVegetable(){
        val name = resources.getStringArray(R.array.name_vegetable)
        val img = resources.obtainTypedArray(R.array.picture_vegetable)
        val description = resources.getStringArray(R.array.description_vegetable)

        itemsvegetable.clear()
        for (i in name.indices){
            itemsvegetable.add(
                Sayur(
                    name[i],
                    description[i],
                    img.getResourceId(i,0)
                )
            )
        }
        img.recycle()
    }
}
