package com.ratushny.poeasisto.ninja.data.model.currencyoverview

data class NinjaCurrencyResponse(
    val lines: List<NinjaCurrencyLines>,
    val currencyDetails: List<NinjaCurrencyDetails>
)