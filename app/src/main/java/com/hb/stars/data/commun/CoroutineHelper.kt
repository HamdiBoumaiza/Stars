package com.hb.stars.data.commun

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * Example found in the travel adviser app
 * https://github.com/RimGazzeh/TravelAdvisor/blob/master/data/src/main/java/com/rim/data/common/CoroutineHelper.kt
 **/

fun <T, V> CoroutineScope.asyncAll(list: List<T>, block: suspend (T) -> V): List<Deferred<V>> {
    return list.map {
        async { block.invoke(it) }
    }
}