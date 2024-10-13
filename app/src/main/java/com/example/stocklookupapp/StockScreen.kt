package com.example.stocklookupapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarWithButton(viewModel: StockViewModel = viewModel()) {


    var query by remember { mutableStateOf("") }
    val stockData by viewModel.stockData.observeAsState()
    val searchResults by viewModel.searchResults.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    var selectedSymbol by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter the symbol") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true
        )
        Button(
            onClick = { if (query.isNotBlank()) {
                viewModel.searchStockSymbol(query)
            } else {
                viewModel.setErrorMessage("Please enter a stock symbol.")
            }
            } ,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.gold),
                contentColor = colorResource(id = R.color.dark_gold)
            )
        ) {
            Text(
                "Search", color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )
        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
        }

        errorMessage?.let {
        Text(text = it, color = MaterialTheme.colorScheme.error)
       }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(searchResults) { result ->
                SearchResultItem(
                    result = result,
                    isSelected = selectedSymbol == result.symbol,
                    stockData = stockData,
                    onClick = { symbol ->
                        selectedSymbol = symbol
                        viewModel.fetchStockData(symbol)
                    }
                )
            }
        }
    }
}

@Composable
fun SearchResultItem(
    result: Match,
    isSelected: Boolean,
    stockData: GlobalQuote?,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(result.symbol) }
    ) {
        Text(text = "SYMBOL: ${result.symbol}", fontWeight = FontWeight.Bold)
        Row {
            Text(
                text = "Company Name: ",
                fontWeight = FontWeight.Bold
            )
            Text(text = result.name)
        }
        if (isSelected && stockData != null && stockData.symbol == result.symbol) {
            Row {
                Text(
                    text = "Current Stock Price: ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = stockData.price)
            }
            Row {
                Text(
                    text = "Percentage Change: ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = stockData.percentChange)
            }
        }
    }
}