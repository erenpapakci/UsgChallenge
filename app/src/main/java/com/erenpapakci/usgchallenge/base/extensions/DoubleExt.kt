package com.erenpapakci.usgchallenge.base.extensions

import java.text.DecimalFormat
import java.text.NumberFormat

fun Double.currencyFormatter(number: Double?): String{
    if (number != null){
        val formatter: NumberFormat = DecimalFormat("###,###,##0.00")
        return formatter.format(number)
    }
    return ""
}