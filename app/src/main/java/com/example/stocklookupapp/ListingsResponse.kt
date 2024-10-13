package com.example.stocklookupapp

import com.google.gson.annotations.SerializedName

data class GlobalQuote(

    @SerializedName("01. symbol")
    val symbol:String,

    @SerializedName( "05. price")
    val price :String,

    @SerializedName("10. change percent")
    val percentChange:String
)

data class GlobalQuoteResponse(
    @SerializedName("Global Quote")
    val globalQuote: GlobalQuote
)

