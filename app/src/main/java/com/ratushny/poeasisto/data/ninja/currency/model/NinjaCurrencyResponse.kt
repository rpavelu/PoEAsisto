package com.ratushny.poeasisto.data.ninja.currency.model

data class NinjaCurrencyResponse(
    val lines: List<NinjaCurrencyLines>,
    val currencyDetails: List<NinjaCurrencyDetails>
)