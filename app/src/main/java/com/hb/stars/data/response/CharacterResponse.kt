package com.hb.stars.data.response

import com.google.gson.annotations.SerializedName
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.utils.UNDEFINED

data class CharacterResponse(
        @SerializedName("name") val name: String?,
        @SerializedName("birth_year") val birthYear: String?,
        @SerializedName("height") val height: String?,
        @SerializedName("homeworld") val homeWorld: String?,
        @SerializedName("films") val films: List<String>?,
        @SerializedName("species") val species: List<String>?
) : DomainMapper<CharacterModel> {
    override fun mapToDomainModel() = CharacterModel(
            name ?: UNDEFINED,
            birthYear ?: UNDEFINED,
            height ?: UNDEFINED,
            homeWorld ?: UNDEFINED,
            films ?: emptyList(),
            species ?: emptyList()
    )
}
