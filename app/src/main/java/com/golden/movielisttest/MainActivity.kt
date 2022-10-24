package com.golden.movielisttest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.golden.movielisttest.model.MovieResponse
import com.golden.movielisttest.model.remote.MovieNetwork
import java.util.concurrent.Executors

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

    private val movieHandler =
        object: Handler()
        {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val listOfMovies: List<MovieResponse> =
                    msg.obj as List<MovieResponse>

                when(msg.what){
                    1 -> {
                        val listOfMovies: List<MovieResponse> = msg.obj as List<MovieResponse>
                        rvMovieList.adapter = MovieAdapter(listOfMovies)
                    }
                    else ->
                    {
                        msg.data?.getString("KEY")?.let{
                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getMovieList()
    }

    private fun initViews(){
        rvMovieList = findViewById(R.id.movie_list)
        //rvMovieList.adapter = MovieAdapter(getMovieList())
        rvMovieList.layoutManager = createLayoutManager()
    }

    private fun getMovieList(){

        val network = MovieNetwork()
        var networkResult = mutableListOf<String>()
        /*val ex = Executors.callable{
          Runnable {
                networkResult =
                    network.getMovieList()
            }
        }*/
        Thread{
            Log.d(TAG, "getMovieList:${Thread.currentThread().name}")
            val message = Message()
            message.what = 1
            message.obj = network.getMovieList()
            movieHandler.sendMessage(message)
        }.start()

        //return ex.call()
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