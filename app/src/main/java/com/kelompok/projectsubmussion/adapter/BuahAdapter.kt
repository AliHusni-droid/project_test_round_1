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
import com.kelompok.projectsubmussion.activity.DetailBuahActivity
import com.kelompok.projectsubmussion.model.Buah
import com.squareup.picasso.Picasso

class BuahAdapter (

    private val context: Context,
    private val item_fruit: List<Buah>
) : RecyclerView.Adapter<BuahAdapter.ViewFruitHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewFruitHolder (
        LayoutInflater.from(context).inflate(R.layout.list_buah,parent,false))

    override fun getItemCount(): Int = item_fruit.size

    override fun onBindViewHolder(holder: ViewFruitHolder, position: Int) {
        holder.bindBuah(item_fruit[position])
    }
    class ViewFruitHolder(view:View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.tv_fruit)
        private val img = view.findViewById<ImageView>(R.id.iv_fruit)
        private val description = view.findViewById<TextView>(R.id.tv_description_fruit)
        private val layout = view.findViewById<RelativeLayout>(R.id.parent_layout_fruit)

        fun bindBuah(item_fruit: Buah){

            layout.setOnClickListener {

                val detaifruit = Intent(itemView.context,DetailBuahActivity::class.java)
                detaifruit.putExtra("txt_detail_fruit",item_fruit.name_fruit)
                detaifruit.putExtra("img__detail_fruit", item_fruit.img_fruit)
                detaifruit.putExtra("txt_detail_desc_fruit", item_fruit.description_fruit)
                itemView.context.startActivity(detaifruit)

            }

            name.text = item_fruit.name_fruit
            description.text = item_fruit.description_fruit
            item_fruit.img_fruit?.let { Picasso.get().load(it).fit().into(img) }
        }
    }
}