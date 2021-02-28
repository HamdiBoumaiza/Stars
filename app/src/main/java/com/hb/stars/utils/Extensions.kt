package com.hb.stars.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.floor
import kotlin.math.roundToInt

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)

/**
 * extension function that make any view visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * extension function that hide any view (gone)
 */
fun View.hide() {
    visibility = View.GONE
}


/**
 * extension function that takes a url string as http and return it with https instead
 */
fun String.convertUrlToHttps() = if (this.isNotEmpty()) {
    substring(0, 4) + 's' + substring(4)
} else {
    UNDEFINED
}

/**
 * extension function for the Toast class that takes a string
 */
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


/**
 * extension function to convert cm to feet
 * 1 foot = 30.48 cm
 * 1 inch = 2.54 cm
 * 1 foot = 12 inches
 */
@Throws(NumberFormatException::class)
fun String.convertCmToFeet(): String {
    val feet = floor(toDouble() / 30.48).toInt()
    val inches = (toDouble() / 2.54 - feet * 12).roundToInt()
    return String.format("%d' %d''", feet, inches)
}

/**
 * check if a string from the remote API has a value
 */
fun String.hasValue() = this != UNDEFINED


/**
 * inline function to convert json string to a TypeToken generic type
 */
inline fun <reified T> Gson.fromJsonToObjectType(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)


/**
 * Listen to change on the edit text and return the value in a state flow
 */
fun EditText.getTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    addTextChangedListener {
        query.value = it.toString()
    }
    return query
}