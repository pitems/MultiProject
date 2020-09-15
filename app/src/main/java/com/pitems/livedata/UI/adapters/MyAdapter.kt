package com.pitems.livedata.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pitems.livedata.R
import com.pitems.livedata.data.model.Post
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    private  var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userId_txt.text=myList[position].userId.toString()
        holder.itemView.Id_txt.text=myList[position].id.toString()
        holder.itemView.title_txt.text=myList[position].title
        holder.itemView.body_txt.text=myList[position].body
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList:List<Post>){
        myList=newList
        notifyDataSetChanged()
    }
}