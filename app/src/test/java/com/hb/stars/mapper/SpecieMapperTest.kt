package com.hb.stars.mapper

import com.google.common.truth.Truth
import com.hb.stars.data.response.SpecieResponse
import org.junit.Test

class SpecieMapperTest {
    private val specieResponse = SpecieResponse(
        name = "Luke Sky",
        language = "lang"
    )
    private val specieModel = specieResponse.mapToDomainModel()

    @Test
    fun carModelMapperTest() {
        Truth.assertThat(specieModel.name).isNotEmpty()
        Truth.assertThat(specieModel.name).matches("Luke Sky")
        Truth.assertThat(specieModel.language).isNotNull()
        Truth.assertThat(specieModel.language).matches("lang")
    }

}