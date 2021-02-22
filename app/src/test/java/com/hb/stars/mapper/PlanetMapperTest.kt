package com.hb.stars.mapper

import com.google.common.truth.Truth
import com.hb.stars.data.response.PlanetResponse
import org.junit.Test

class PlanetMapperTest {
    private val planetResponse = PlanetResponse(
        population = "120000"
    )
    private val planetModel = planetResponse.mapToDomainModel()

    @Test
    fun planetModelMapperTest() {
        Truth.assertThat(planetModel.population).matches("12000")
    }

}