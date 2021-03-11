package com.hb.stars.data.response

import com.google.gson.annotations.SerializedName
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.utils.UNDEFINED

data class MovieResponse(
        @SerializedName("title") val title: String?,
        @SerializedName("opening_crawl") val description: String?
) : DomainMapper<MovieModel> {
    override fun mapToDomainModel() = MovieModel(title ?: UNDEFINED, description
            ?: UNDEFINED, false)
}
