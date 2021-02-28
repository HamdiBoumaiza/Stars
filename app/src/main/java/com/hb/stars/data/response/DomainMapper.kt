package com.hb.stars.data.response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}
