package com.golden.movielisttest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.golden.movielisttest.model.MovieResponse
import com.squareup.picasso3.Picasso

class MovieAdapter(private val dataset: List<MovieResponse>): RecyclerView.Adapter<MovieViewHolder>() {

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

        holder.tvMovieTitle.text = dataset[position].title
        holder.tvRelleaseYear.text = dataset[position].releaseYear.toString()
        holder.rbRatingBar.rating = dataset[position].rating
        holder.tvGenre.text = dataset[position].genre.toString()

        holder.fbShowDetail.setOnClickListener{
            holder.wgDetailGroup.visibility = View.VISIBLE
        }


            Picasso.Builder(holder.itemView.context)
                .build()
                .load(dataset[position].image)
                .into(holder.imgMoviePoster)

    }
    //end of class

        override fun getItemCount(): Int {
            return dataset.size
        }

}