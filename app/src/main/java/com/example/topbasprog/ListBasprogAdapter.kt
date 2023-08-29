package com.example.topbasprog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ListBasprogAdapter(private val listBasprog: ArrayList<Basprog>): RecyclerView.Adapter<ListBasprogAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var indexItem = 0

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Basprog)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNo: TextView = itemView.findViewById(R.id.tvNo)
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgTitle)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val cardView: CardView  = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_basprog, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBasprog.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listBasprog[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.tvNo.text = (position + indexItem + 1).toString() + ". "
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listBasprog[holder.adapterPosition])
        }
        holder.cardView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.cardView.context,
                R.anim.animscroll
            )
        )
    }
}