# Hyperliquid Java Client

A lightweight Java library designed to interface with the Hyperliquid decentralized exchange (DEX). This bridge provides a seamless way to trigger crypto market actions via Java endpoints, enabling programmatic trading and account management.

## Features

* **Order/Position Management**: Create and cancel orders/positions on the Hyperliquid market.
* **Balance Verification**: Programmatically check account balances and asset positions.
* **API Integration**: Simplified wrapper for Hyperliquid's REST API.
* **Websocket Update**: Subscribe to real-time order books, trades, and account updates
* **Java Native**: Built for easy integration into existing Spring Boot or standalone Java applications.

## Getting Started

### Prerequisites

* JDK 11 or higher
* Maven
* Hyperliquid Wallet credentials

### Installation

Clone the repository and install it to your local Maven repository:

```bash
git clone https://github.com/Wayruha/hyper-liquid-client.git
cd hyper-liquid-client
mvn clean install
```

### Examples

You can find simple examples in `src/test` directory.