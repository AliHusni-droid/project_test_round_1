package com.kelompok.projectsubmussion.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kelompok.projectsubmussion.R
import com.kelompok.projectsubmussion.activity.DetailSayurActivity
import com.kelompok.projectsubmussion.model.Sayur
import com.squareup.picasso.Picasso

class SayurAdapter (

    private val context: Context,
    private val item_vegetable: List<Sayur>
) : RecyclerView.Adapter<SayurAdapter.ViewVegetableHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewVegetableHolder (
        LayoutInflater.from(context).inflate(R.layout.list_sayur,parent,false))

    override fun getItemCount(): Int = item_vegetable.size

    override fun onBindViewHolder(holder: ViewVegetableHolder, position: Int) {
        holder.bindSayur(item_vegetable[position])
    }
    class ViewVegetableHolder(view:View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.tv_vegetable)
        private val img = view.findViewById<ImageView>(R.id.iv_vegetable)
        private val description = view.findViewById<TextView>(R.id.tv_description_vegetable)
        private val layout = view.findViewById<RelativeLayout>(R.id.parent_layout_vegetable)

        fun bindSayur(item_vegetable: Sayur){

            layout.setOnClickListener {

                val detaivegetable = Intent(itemView.context,DetailSayurActivity::class.java)
                detaivegetable.putExtra("txt_detail_vegetable",item_vegetable.name_vegetable)
                detaivegetable.putExtra("img__detail_vegetable", item_vegetable.img_vegetable)
                detaivegetable.putExtra("txt_detail_desc_vegetable", item_vegetable.description_vegetable)
                itemView.context.startActivity(detaivegetable)

            }

            name.text = item_vegetable.name_vegetable
            description.text = item_vegetable.description_vegetable
            item_vegetable.img_vegetable?.let { Picasso.get().load(it).fit().into(img) }
        }
    }
}