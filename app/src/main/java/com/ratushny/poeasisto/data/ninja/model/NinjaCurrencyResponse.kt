package com.ratushny.poeasisto.data.ninja.model

data class NinjaCurrencyResponse(
    val lines: List<NinjaCurrencyLines>,
    val currencyDetails: List<NinjaCurrencyDetails>
)