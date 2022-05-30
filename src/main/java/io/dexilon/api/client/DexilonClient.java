package io.dexilon.api.client;

import io.dexilon.api.client.model.OrderBookInfo;
import io.dexilon.api.client.model.SymbolItem;

import java.util.List;

public interface DexilonClient {
    List<SymbolItem> getAllSymbols();

    OrderBookInfo getOrderBook(String orderBookSymbol);
}
