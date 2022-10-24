package com.golden.movielisttest

import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
    val tvMovieTitle: TextView = view.findViewById(R.id.tv_movie_title)
    val imgMoviePoster: ImageView = view.findViewById(R.id.img_movie_poster)
    val rbRatingBar: RatingBar = view.findViewById(R.id.rb_movie_rating)
    val tvRelleaseYear: TextView = view.findViewById(R.id.tv_release_year)
    val tvGenre: TextView = view.findViewById(R.id.tv_movie_genre)
    val fbShowDetail: FloatingActionButton = view.findViewById(R.id.btn_show_detail)
    val wgDetailGroup: Group = view.findViewById(R.id.wg_movie_group)
}