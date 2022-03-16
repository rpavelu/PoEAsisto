package com.ratushny.poeasisto.ninja.data.model.itemoverview

data class NinjaItemLines(
    val name: String,
    val icon: String,
    val sparkline: NinjaItemReceiveSparkLine,
    val chaosValue: Float,
    val exaltedValue: Double
)