package com.hb.stars.data.response

import com.google.gson.annotations.SerializedName
import com.hb.stars.domain.models.SpecieModel
import com.hb.stars.utils.UNDEFINED

data class SpecieResponse(
        @SerializedName("name") val name: String?,
        @SerializedName("language") val language: String?
) : DomainMapper<SpecieModel> {
    override fun mapToDomainModel() = SpecieModel(name ?: UNDEFINED, language ?: UNDEFINED)
}
