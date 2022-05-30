package io.dexilon.api.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationIntegrationTest {

    public static final String TEST_METAMASK_ADDRESS = "0x201d980aeD5C04a7e75860cFE29CeD9b5da05A08";
    public static final String TEST_PRIVATE_KEY = "87d25c8ade8c4bb32be098bb35cd594fd1c0511c4423bf36f006f4ecd27f017c";

    private static final String TEST_DEV2_URL = "https://dex-dev2-api.cronrate.com/api/v1";

    private static final String TEST_NONCE = "EUYtmtJags5TWbE";
    private static final String SIGNED_NONCE = "0x11368f9f43b9e90f4da02259f2994db66a339f6e744eac6ca570ccbb96a893440c8ea9e7486d39208d212851c0a7b71f3e57fd3f33b788ae20439f4b84e19f0c1c";

    private DexilonClientImpl testInstance;

    @BeforeEach
    public void setUp() {
        testInstance = new DexilonClientImpl(TEST_METAMASK_ADDRESS, TEST_PRIVATE_KEY, TEST_DEV2_URL);
    }

    @Test
    public void shouldAuthenticate() {
        testInstance.authenticate();
    }

    @Test
    public void shouldSignNonceProperly() {
        String signedNonce = testInstance.signNonce(TEST_NONCE);
        assertEquals(SIGNED_NONCE, signedNonce);
    }

}
