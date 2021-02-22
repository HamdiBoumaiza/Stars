package com.hb.stars.mapper

import com.google.common.truth.Truth
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.utils.UNDEFINED
import org.junit.Test

class MovieMapperTest {
    private val movieResponse = MovieResponse(
        title = "The last one",
        description = ""

    )
    private val movieModel = movieResponse.mapToDomainModel()

    @Test
    fun movieModelMapperTest() {
        Truth.assertThat(movieModel.title).matches("Luke Sky")
        Truth.assertThat(movieModel.description).matches(UNDEFINED)
    }

}