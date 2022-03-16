package com.ratushny.poeasisto.ninja.data.model.currencyoverview

data class NinjaCurrencyLines(
    val currencyTypeName: String,
    val receive: NinjaCurrencyReceive,
    val receiveSparkLine: NinjaCurrencyReceiveSparkLine
)