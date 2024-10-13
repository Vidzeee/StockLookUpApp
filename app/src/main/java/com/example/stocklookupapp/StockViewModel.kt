package com.example.stocklookupapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    private val _stockData = MutableLiveData<GlobalQuote?>()
    val stockData: LiveData<GlobalQuote?> get() = _stockData

    private val _searchResults = MutableLiveData<List<Match>>()
    val searchResults: LiveData<List<Match>> get() = _searchResults

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _apiKey = "JZ4833WHP49UFW0V"

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun searchStockSymbol(keywords: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.create().getName(keywords, _apiKey)

                Log.d("APIIIII", "API Request: ${keywords},${_apiKey}")
                Log.d("StockViewModel", "API Response: ${response.body()}")
                Log.d("API Response", response.body().toString())


               if (response.isSuccessful && response.body() != null) {

                    if (response.body()?.bestMatches.isNullOrEmpty()) {
                        _errorMessage.value = "Invalid stock symbol."
                    } else {
                        _searchResults.value = response.body()?.bestMatches ?: emptyList()
                        _errorMessage.value = null
                    }
                }
            }
             catch (e: Exception) {
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchStockData(symbol: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.createService().getListings(symbol, _apiKey)
                if (response.isSuccessful && response.body() != null) {
                    _stockData.value = response.body()?.globalQuote
                    _errorMessage.value = null
                }
                else {
                    _errorMessage.value = "Error fetching stock data"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}



