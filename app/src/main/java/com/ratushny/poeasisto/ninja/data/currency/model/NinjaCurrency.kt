package com.ratushny.poeasisto.ninja.data.currency.model

data class NinjaCurrency(
    val currencyTypeName: String,
    val currencyValue: Double,
    val currencyImage: String,
    val currencyChange: Double
)