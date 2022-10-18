package com.golden.movielisttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/*
    Create an Item_ layout
    Create the  subclass RecyclerView.ViewHolder
    Create the subclass RECYCLERVIEW.ADAPTER
    Configure the movieList view
    setAdapter
    SetLayoutManager
     */

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovieList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        rvMovieList = findViewById(R.id.movie_list)
        rvMovieList.adapter = MovieAdapter(generateData())
        rvMovieList.layoutManager = createLayoutManager()
    }

    private fun generateData(): List<String> {
        return (0..9).map{
            "Movie with the name: $it"
        }

    }

    private fun createLayoutManager():RecyclerView.LayoutManager{
        val linearLayoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,
            false)

        val gridLayoutManager = GridLayoutManager(this,
            3,
            GridLayoutManager.HORIZONTAL,
            true)

        val staggeredGrid = StaggeredGridLayoutManager(3,
            StaggeredGridLayoutManager.HORIZONTAL)

        return gridLayoutManager
    }

}