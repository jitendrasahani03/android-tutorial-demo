package com.example.recycleviewdemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var name:List<PersonData>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(view)
    }

    fun updateList(newData:List<PersonData>){
        this.name = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val person = name[position]
            holder.bind(person,position)
    }


    override fun getItemCount(): Int {
            return name.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewFirst = itemView.findViewById<TextView>(R.id.textViewFirst)
        var listContainer = itemView.findViewById<LinearLayout>(R.id.container)

        fun bind(item: PersonData,position: Int){
            textViewFirst.text = item.firstName
            var color = "#CCCCCC"
            if (position%2==0){
                color = "#FFCC80"
            }
            listContainer.setBackgroundColor(Color.parseColor(color))
        }
    }
}