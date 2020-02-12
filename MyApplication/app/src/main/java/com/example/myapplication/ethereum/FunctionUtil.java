package com.example.myapplication.ethereum;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class FunctionUtil {

    public static String CONTRACT_ADDRESS = "";

    public static Function createGetPostCountSmartContractCall() {
        return new Function("getPostCount"
                , Collections.emptyList()
                , Collections.singletonList(new TypeReference<Uint>() {
        }));
    }

    public static Function createGetPostSmartContractCall(int index) {
        return new Function("getPost"
                , Collections.singletonList(new Uint(BigInteger.valueOf(index)))
                , Arrays.asList(
                new TypeReference<Utf8String>() {
                }
                , new TypeReference<Utf8String>() {
                }
                , new TypeReference<Utf8String>() {
                }
                , new TypeReference<Utf8String>() {
                }
        ));
}
