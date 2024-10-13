package com.example.stocklookupapp

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("1. symbol")
    val symbol: String,

    @SerializedName("2. name")
    val name: String
)

data class SymbolSearchResponse(
    val bestMatches: List<Match>
)
