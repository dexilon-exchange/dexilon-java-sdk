package io.dexilon.api.client;

import io.dexilon.api.client.model.*;
import io.dexilon.api.client.model.exception.DexilonAuthenticationException;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

public class DexilonClientImpl implements DexilonClient{

    private static final String DEFAULT_URL = "https://dex-dev-api.cronrate.com/api/v1";
    public static final String SYMBOLS_CONTEXT = "/symbols";
    public static final String ORDERS_BOOK_CONTEXT = "/orders/book?symbol={orderBookSymbol}";
    public static final String START_AUTH_CONTEXT = "/auth/startAuth";
    public static final String FINISH_AUTH_CONTEXT = "/auth/finishAuth";

    private final String metamaskAddress;
    private final String privateKey;
    private final String apiUrl;

    FinishAuthenticationInfo authenticationInfo;

    private final RestTemplate restTemplate;

    public DexilonClientImpl(String metamaskAddress, String privateKey, String apiUrl) {
        this.metamaskAddress = metamaskAddress;
        this.privateKey = privateKey;
        this.apiUrl = apiUrl;
        restTemplate = new RestTemplate();
    }

    public DexilonClientImpl(String metamaskAddress, String privateKey) {
        this.metamaskAddress = metamaskAddress;
        this.privateKey = privateKey;
        this.apiUrl = DEFAULT_URL;
        restTemplate = new RestTemplate();
    }

    void check_authentication() {
        if (authenticationInfo == null) {
            authenticate();
        }
    }

    void authenticate() {
        Optional<String> nonceResponse = startAuthentication();
        if(nonceResponse.isEmpty()) {
            throw new DexilonAuthenticationException("There is an issue trying to get a nonce value");
        }
        Optional<FinishAuthenticationInfo> finishAuthenticationInfo = finishAuthentication(signNonce(nonceResponse.get()));
        if(finishAuthenticationInfo.isEmpty()) {
            throw new DexilonAuthenticationException("There is an issue getting GWT token from authentication server");
        }
        authenticationInfo = finishAuthenticationInfo.get();
    }

    String signNonce(String nonce) {
        //TODO: sign nonce with web3 method

//        byte[] r = Numeric.hexStringToByteArray(StringUtils.substring(signedNonce, 0, 66));
//        byte[] s = Numeric.hexStringToByteArray("0x" + StringUtils.substring(signedNonce, 66, 130));
//        byte[] v = Numeric.hexStringToByteArray("0x" + StringUtils.substring(signedNonce, 130, 132));

//        byte[] result = new byte[prefix.length + message.length];
//        System.arraycopy(prefix, 0, result, 0, prefix.length);
//        System.arraycopy(message, 0, result, prefix.length, message.length);

        BigInteger publicKey = Sign.publicKeyFromPrivate(Numeric.toBigInt(privateKey));
        Sign.SignatureData signatureData = Sign.signMessage(nonce.getBytes(), new ECKeyPair(Numeric.toBigInt(privateKey), publicKey));

        byte[] result = new byte[signatureData.getR().length + signatureData.getS().length + signatureData.getV().length];
        System.arraycopy(signatureData.getR(), 0, result, 0, signatureData.getR().length);
        System.arraycopy(signatureData.getS(), 0, result, signatureData.getR().length, signatureData.getS().length);
        System.arraycopy(signatureData.getV(), 0, result, signatureData.getR().length + signatureData.getR().length, signatureData.getV().length);

        String hexString = Numeric.toHexString(result);

        return hexString;
    }

    private Optional<FinishAuthenticationInfo> finishAuthentication(String signedNonce) {
        FinishAuthenticationResponse finishAuthResponse = restTemplate.postForObject(apiUrl + FINISH_AUTH_CONTEXT, FinishAuthenticationRequest.builder().metamaskAddress(metamaskAddress).signedNonce(signedNonce).build(), FinishAuthenticationResponse.class);
        return finishAuthResponse != null ? Optional.ofNullable(finishAuthResponse.getBody()) : Optional.empty();
    }

    private Optional<String> startAuthentication() {
        StartAuthResponse startAuthResponse = restTemplate.postForObject(apiUrl + START_AUTH_CONTEXT, MetamaskAddressRequest.builder().metamaskAddress(metamaskAddress).build(), StartAuthResponse.class);
        return startAuthResponse != null ? Optional.ofNullable(startAuthResponse.getNonce()) : Optional.empty();
    }

    @Override
    public List<SymbolItem> getAllSymbols() {
        AllSymbolResponse allSymbolsResponse = restTemplate.getForObject(apiUrl + SYMBOLS_CONTEXT, AllSymbolResponse.class);
        return allSymbolsResponse != null ? allSymbolsResponse.getBody() : List.of();
    }

    @Override
    public OrderBookInfo getOrderBook(String orderBookSymbol) {
        OrderBookResponse orderBookResponse = restTemplate.getForObject(apiUrl + ORDERS_BOOK_CONTEXT, OrderBookResponse.class, orderBookSymbol);
        return orderBookResponse != null ? orderBookResponse.getBody() : null;
    }
}
