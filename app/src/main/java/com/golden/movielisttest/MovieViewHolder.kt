package com.golden.movielisttest

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
    val tvMovieTitle: TextView = view.findViewById(R.id.movie_title)
}