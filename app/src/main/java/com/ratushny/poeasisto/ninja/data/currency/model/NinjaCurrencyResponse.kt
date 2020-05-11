package com.ratushny.poeasisto.ninja.data.currency.model

data class NinjaCurrencyResponse(
    val lines: List<NinjaCurrencyLines>,
    val currencyDetails: List<NinjaCurrencyDetails>
)