package com.erenpapakci.usgchallenge.base.extensions

import kotlin.math.roundToInt

fun Double.twoDigit(number: Double?): Double{
    if (number != null){
        return (number * 100.0).roundToInt() / 100.0
    }
    return 0.0
}