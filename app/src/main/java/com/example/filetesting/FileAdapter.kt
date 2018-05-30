package com.example.filetesting

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_item_file.view.*

class FileAdapter(val context: Context): RecyclerView.Adapter<FileAdapter.FileViewHolder>(){
    val list = ArrayList<FileModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.adapter_item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.itemView.title.text = list[position].name
        holder.itemView.size.text = list[position].size
    }

    fun updateList(list: List<FileModel>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class FileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}