package com.golden.movielisttest.model.remote

import android.net.Uri
import com.golden.movielisttest.model.MovieResponse
import com.golden.movielisttest.model.Response
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MovieNetwork {
    companion object{
        const val BASE_URL = "https://gist.githubusercontent.com/"
        const val ENDPOINT = "AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/a1403b33a4c0d9a40d1901f6aeba065abc748a38/MovieGist"
        val uriPath = Uri.parse("$BASE_URL$ENDPOINT")
        val url = URL(uriPath.toString())
    }

    fun getMovieList():List<MovieResponse>{
        val httpUrlConnection = url.openConnection() as HttpURLConnection

        httpUrlConnection.readTimeout = 10000
        httpUrlConnection.connectTimeout = 15000
        httpUrlConnection.requestMethod = "GET"
        httpUrlConnection.doInput = true

        httpUrlConnection.connect()

        return httpUrlConnection.inputStream.run{
            deserializeIS(this)
        }.let { parseToMovieResponse(it) }
    }

    fun deserializeIS(IS: InputStream): String{
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(IS))
        var line: String? = reader.readLine()
        while(line != null){
             builder.append(line)
             builder.append("\n")
             line = reader.readLine()

         }

        if (builder.length == 0) {
            return "404"
        }
        return builder.toString()
    }

    fun parseToMovieResponse(deserializeIS: String): List<MovieResponse>
    {
        val movieResponse = JSONArray(deserializeIS)
        val listofMovies = mutableListOf<MovieResponse>()

        for (index in 0 until movieResponse.length())
        {
           val movieElement = movieResponse.getJSONObject(index)
            val movieResponseList = Response()
           val movieResponse = MovieResponse(
               rating = movieElement.getDouble("rating").toFloat(),
               title = movieElement.getString("title"),
               image = movieElement.getString("image"),
               releaseYear = movieElement.getInt("releaseYear"),
               genre = movieElement.getJSONArray("genre").parseJSONtoList()
           )
            listofMovies.add(movieResponse)
        }
        return listofMovies
    }

    private fun JSONArray.parseJSONtoList(): List<String>
    {
        val result = mutableListOf<String>()
        for(index in 0 until this.length())
        {
            result.add(this.getString(index))
        }
        return result
    }
}
//https://gist.githubusercontent.com/AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/6576691177f6b093afd3bf2bbc5e936b62d50721/MovieGist
/*
StringBuilder builder = new StringBuilder();
BufferedReader reader =
    new BufferedReader(new InputStreamReader(inputStream));
String line;
while ((line = reader.readLine()) != null) {
   builder.append(line + "\n");
}
if (builder.length() == 0) {
   return null;
}
resultString = builder.toString();
 */