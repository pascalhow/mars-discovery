package com.pascalhow.marsdiscovery.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.changeFormat(oldFormat: String, newFormat: String): String {
    val parser = SimpleDateFormat(oldFormat, Locale.ENGLISH)
    val formatter = SimpleDateFormat(newFormat, Locale.ENGLISH)
    return formatter.format(parser.parse(this))
}
