package com.mydemo.marketdemo.http;

/**
 * Created by chenlong on 2016/12/19.
 */

public class HttpConstant
{
    /**
     * 本地localhost
     */
    public static final String BASE_URL ="http://10.0.2.2:8080/market/";
    /**
     * okHttp的缓存地址
     */
    public static final String CACHE_DIR = "marketCache";

    /**
     * okHttp的缓存大小 默认10m
     */
    public static final int CACHE_SIZE = 1024 * 1024 * 10;
}
