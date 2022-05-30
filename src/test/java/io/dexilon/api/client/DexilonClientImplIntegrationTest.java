package io.dexilon.api.client;

import io.dexilon.api.client.model.OrderBookInfo;
import io.dexilon.api.client.model.OrderBookResponse;
import io.dexilon.api.client.model.SymbolItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DexilonClientImplIntegrationTest {

    public static final String TEST_METAMASK_ADDRESS = "0x201d980aeD5C04a7e75860cFE29CeD9b5da05A08";
    public static final String TEST_PRIVATE_KEY = "87d25c8ade8c4bb32be098bb35cd594fd1c0511c4423bf36f006f4ecd27f017c";

    private static final String TEST_DEV2_URL = "https://dex-dev2-api.cronrate.com/api/v1";

    private DexilonClientImpl testInstance;

    @BeforeEach
    public void setUp() {
        testInstance = new DexilonClientImpl(TEST_METAMASK_ADDRESS, TEST_PRIVATE_KEY, TEST_DEV2_URL);
    }

    @Test
    public void shouldGetAllSymbols() {
        List<SymbolItem> allSymbols =  testInstance.getAllSymbols();
        assertFalse(allSymbols.isEmpty());
    }

    @Test
    public void shouldGetOrderBook() {
        OrderBookInfo orderBookResult = testInstance.getOrderBook("sol_usdc");
        assertFalse(orderBookResult.getAsk().isEmpty());
        assertFalse(orderBookResult.getBid().isEmpty());
    }

}
