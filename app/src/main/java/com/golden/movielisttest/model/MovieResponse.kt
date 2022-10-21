package com.golden.movielisttest.model

/**
 * In-memory data representation
 * toString()
 * hshCode()
 * equals()
 * copy()
 * componentNt()
 * can't be inherited
 * needs at least 1 field/property
 */
data class MovieResponse(
    val title: String,
    val image: String,
    val rating: Float,
    val releaseYear: Int,
    val genre: List<String>
)

class Response: ArrayList<MovieResponse>()
