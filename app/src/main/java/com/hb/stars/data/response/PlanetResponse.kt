package com.hb.stars.data.response

import com.google.gson.annotations.SerializedName
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.utils.UNDEFINED

data class PlanetResponse(
        @SerializedName("population") val population: String?
) : DomainMapper<PlanetModel> {
    override fun mapToDomainModel() = PlanetModel(population ?: UNDEFINED)
}
