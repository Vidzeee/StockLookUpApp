package com.example.stocklookupapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("query?function=SYMBOL_SEARCH")
    suspend fun getName(
        @Query("keywords") keywords: String,
        @Query("apikey") apiKey: String
    ): Response<SymbolSearchResponse>


    @GET("query?function=GLOBAL_QUOTE")
    suspend fun getListings(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String
    ): Response<GlobalQuoteResponse>
}