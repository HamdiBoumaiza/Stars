package com.hb.stars.data.commun

import com.hb.stars.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart


/**
 * extension function for Flow Class to emit loading state before the flow starts
 */
fun <T> Flow<StarWarsResult<T>>.onFlowStarts() =
    onStart {
        emit(StarWarsResult.Loading)
    }.catch {
        emit(StarWarsResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message)))
    }
