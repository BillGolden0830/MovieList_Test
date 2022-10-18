package com.golden.movielisttest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val dataset: List<String>): RecyclerView.Adapter<MovieViewHolder>() {

    /*
    Uset to create the ViewHolder
    This will only be created once.
    If the structure of the ViewHolder is changed, this will be called agaijn.
    Get #LayoutInflater.from(conext)
    Needs to be from Base context (ContextThemeWrapper)
    Base context provides themes, resources, inflater
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false) )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.BLUE)
        }
            holder.tvMovieTitle.text = dataset[position]
    }

        override fun getItemCount(): Int {
            return dataset.size
        }

}