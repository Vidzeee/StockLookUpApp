# Stock Lookup App

A simple Android app that allows users to search for real-time stock market data, such as the current stock price, percentage change, and company information. This app integrates with the [Alpha Vantage](https://www.alphavantage.co/) API to fetch stock market data.

---

## Table of Contents

1. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)
4. [API Reference](#api-reference)
5. [Error Handling](#error-handling)
6. [Screenshots](#screenshots)
---

## Features

- Real-time stock search using the **Alpha Vantage** API.
- Displays stock price, percentage change, and company name.
- Simple, user-friendly UI built with standard Android components (no Jetpack Compose).
- Handles API call errors and shows loading states during data fetching.

---

## Installation

To get the app up and running on your local machine:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/stock-lookup-app.git
   cd stock-lookup-app
   ```

2. **Open the project**:  
   Open the project in Android Studio.

3. **Set up the API key**:  
   Get your API key from [Alpha Vantage](https://www.alphavantage.co/support/#api-key) and add it to the `gradle.properties` file:
   ```
   ALPHA_VANTAGE_API_KEY="your_api_key_here"
   ```

4. **Build and run**:  
   Run the app on your preferred Android device or emulator.

---

## Usage

1. **Enter a stock ticker symbol** (e.g., "AAPL" for Apple) in the search bar.
2. **Click the search button** to fetch the stock data.
3. The app will display the current stock price, percentage change, and company name.

---

## API Reference

This app uses the Alpha Vantage API to retrieve stock data. Below are the key details of the API usage:

### Endpoint

```http
GET https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords={keywords}&apikey={api_key}
GET https://www.alphavantage.co/query?function=GLOBAL_QUOTE&keywords={symbol}&apikey={api_key}
```

- **Parameters**:
  - `symbol` (required): The stock ticker symbol (e.g., "AAPL").
  - `apikey` (required): Your personal API key from Alpha Vantage.

- **Sample Response**:
  ```json   (NameResponse)-
  {
    "bestMatches": [
        {
            "1. symbol": "TSCO.LON",
            "2. name": "Tesco PLC",
            "3. type": "Equity",
            "4. region": "United Kingdom",
            "5. marketOpen": "08:00",
            "6. marketClose": "16:30",
            "7. timezone": "UTC+01",
            "8. currency": "GBX",
            "9. matchScore": "0.7273"
        },
        {
            "1. symbol": "TSCDF",
            "2. name": "Tesco plc",
            "3. type": "Equity",
            "4. region": "United States",
            "5. marketOpen": "09:30",
            "6. marketClose": "16:00",
            "7. timezone": "UTC-04",
            "8. currency": "USD",
            "9. matchScore": "0.7143"
        }
  }

  (Global Quote)-
  {
    "Global Quote": {
        "01. symbol": "IBM",
        "02. open": "233.2500",
        "03. high": "233.4400",
        "04. low": "230.4600",
        "05. price": "233.2600",
        "06. volume": "3469322",
        "07. latest trading day": "2024-10-11",
        "08. previous close": "233.0200",
        "09. change": "0.2400",
        "10. change percent": "0.1030%"
    }
}
  ```

---

## Error Handling

- If the API call fails (e.g., network issues or invalid stock symbol), the app displays an error message.
- The app shows a loading indicator while fetching the stock data.
- All API errors are gracefully handled and reported back to the user.

---

## Screenshots
