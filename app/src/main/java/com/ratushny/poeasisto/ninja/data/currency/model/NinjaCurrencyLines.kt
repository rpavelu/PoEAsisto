package com.ratushny.poeasisto.ninja.data.currency.model

data class NinjaCurrencyLines(
    val currencyTypeName: String,
    val receive: NinjaCurrencyReceive,
    val receiveSparkLine: NinjaCurrencyReceiveSparkLine
)