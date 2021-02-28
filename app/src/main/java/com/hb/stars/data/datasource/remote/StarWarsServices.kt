package com.hb.stars.data.datasource.remote

import com.hb.stars.data.commun.GET_SEARCH_CHARACTERS_URL
import com.hb.stars.data.response.ListCharacterResponse
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.data.response.PlanetResponse
import com.hb.stars.data.response.SpecieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsServices {

    @GET(GET_SEARCH_CHARACTERS_URL)
    suspend fun searchCharacters(@Query("search") input: String): Response<ListCharacterResponse>

    @GET
    suspend fun getPlanet(@Url planetUrl: String): Response<PlanetResponse>

    @GET
    suspend fun getSpecie(@Url specieUrl: String): Response<SpecieResponse>

    @GET
    suspend fun getMovie(@Url movieUrl: String): Response<MovieResponse>
}
