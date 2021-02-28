package com.hb.stars.data.commun

sealed class StarWarsResult<out T> {
    data class Success<out T>(val data: T) : StarWarsResult<T>()
    data class Error(val exception: DataSourceException) : StarWarsResult<Nothing>()
    object Loading : StarWarsResult<Nothing>()
}

inline fun <T : Any> StarWarsResult<T>.onSuccess(action: (T) -> Unit): StarWarsResult<T> {
    if (this is StarWarsResult.Success) action(data)
    return this
}

inline fun <T : Any> StarWarsResult<T>.onError(action: (DataSourceException) -> Unit): StarWarsResult<T> {
    if (this is StarWarsResult.Error) action(exception)
    return this
}

inline fun <T : Any> StarWarsResult<T>.onLoading(action: () -> Unit): StarWarsResult<T> {
    if (this is StarWarsResult.Loading) action()
    return this
}
