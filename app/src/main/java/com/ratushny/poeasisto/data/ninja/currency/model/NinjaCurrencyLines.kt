package com.ratushny.poeasisto.data.ninja.currency.model

data class NinjaCurrencyLines(
    val currencyTypeName: String,
    val receive: NinjaCurrencyReceive,
    val receiveSparkLine: NinjaCurrencyReceiveSparkLine
)