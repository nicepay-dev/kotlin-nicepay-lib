package io.github.nicepay.utils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

object ApiUtils {

    private val print = LoggerPrint()
    val mapper = ObjectMapper()
    private val gson = GsonBuilder().setPrettyPrinting().create()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(Duration.ofSeconds(10))

    private val timeoutClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MILLISECONDS)
        .readTimeout(1, TimeUnit.MILLISECONDS)
        .writeTimeout(1, TimeUnit.MILLISECONDS)

    // == Retrofit Builder ==
    private fun buildRetrofit(baseUrl: String, client: OkHttpClient.Builder): Retrofit =
        retrofitBuilder.client(client.build()).baseUrl(baseUrl).build()

    // == Main entry point ==
    fun <S> createService(
        serviceClass: Class<S>,
        grantType: String?,
        accessToken: String?,
        data: String?,
        config: NICEPay
    ): S {
        httpClient.interceptors().clear()
        httpClient.addInterceptor(requestInterceptor(grantType, accessToken, data, config))
        return buildRetrofit(config.getNICEPayBaseUrl(), httpClient).create(serviceClass)
    }

    fun <S> createTimeoutService(
        serviceClass: Class<S>,
        grantType: String?,
        accessToken: String?,
        data: String?,
        config: NICEPay
    ): S {
        timeoutClient.interceptors().clear()
        timeoutClient.addInterceptor(requestInterceptor(grantType, accessToken, data, config, isTimeout = true))
        return buildRetrofit(config.getNICEPayBaseUrl(), timeoutClient).create(serviceClass)
    }

    fun <S> createServiceV2(serviceClass: Class<S>, config: NICEPay): S {
        httpClient.interceptors().clear()
        httpClient.addInterceptor(loggingInterceptor("V2"))
        return buildRetrofit(config.getNICEPayBaseUrl(), httpClient).create(serviceClass)
    }

    fun <S> createServiceV1(serviceClass: Class<S>, config: NICEPay): S {
        httpClient.interceptors().clear()
        httpClient.addInterceptor(loggingInterceptor("V1"))
        return buildRetrofit(config.getNICEPayBaseUrl(), httpClient).create(serviceClass)
    }

    private fun requestInterceptor(
        grantType: String?,
        accessToken: String?,
        data: String?,
        config: NICEPay,
        isTimeout: Boolean = false
    ): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val pathUrl = original.url.encodedPath.replace("/nicepay", "")
        val headers = getHeaders(original.method, grantType, accessToken, data, pathUrl, config)

        val request = original.newBuilder().headers(headers).build()
        val response = chain.proceed(request)

        val bodyString = response.body?.string() ?: ""
        val contentType = response.body?.contentType()
        val wrappedBody = ResponseBody.create(contentType, bodyString)

        val logPrefix = if (isTimeout) "Request Data Timeout" else "Request Data"
        val args = original.tag(Invocation::class.java)?.arguments()?.firstOrNull()
        print.logInfo("url : ${original.url}")
        print.logInfoBody("$logPrefix:\n${gson.toJson(args)}")

        response.newBuilder().body(wrappedBody).build()
    }

    private fun loggingInterceptor(version: String): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        val bodyString = response.body?.string() ?: ""
        val contentType = response.body?.contentType()
        val wrappedBody = ResponseBody.create(contentType, bodyString)

        val args = request.tag(Invocation::class.java)?.arguments()
        val tag = "Request Data V$version"
        print.logInfo("$tag:\n${gson.toJson(args)}")

        response.newBuilder().body(wrappedBody).build()
    }

    // == Header generation based on grant type or signature auth ==
    @Throws(Exception::class)
    private fun getHeaders(
        method: String,
        grantType: String?,
        accessToken: String?,
        data: String?,
        pathUrl: String,
        config: NICEPay
    ): Headers {
        val headersMap = mutableMapOf<String, String>()
        val partnerId = config.partnerId.toString()
        val privateKey = config.privateKey.toString()
        val clientSecret = config.clientSecret.toString()
        val externalId = config.externalID.toString()
        val timestamp = config.timestamp.toString()

        headersMap["Content-Type"] = "application/json"

        if (grantType != null) {
            val toSign = "$partnerId|$timestamp"
            val signature = SignatureUtils.signSHA256RSA(toSign, privateKey)
            headersMap["X-TIMESTAMP"] = timestamp
            headersMap["X-CLIENT-KEY"] = partnerId
            headersMap["X-SIGNATURE"] = signature
        } else {
            val hashedData = SignatureUtils.sha256EncodeHex(data ?: "")
            val signature = SignatureUtils.getSignature(
                method, accessToken ?: "", hashedData, pathUrl, timestamp, clientSecret
            )
            headersMap["Authorization"] = "Bearer $accessToken"
            headersMap["X-TIMESTAMP"] = timestamp
            headersMap["X-SIGNATURE"] = signature
            headersMap["X-PARTNER-ID"] = partnerId
            headersMap["X-EXTERNAL-ID"] = externalId
            headersMap["CHANNEL-ID"] = partnerId + "01"
        }

        print.logInfoHeader("Request Header:\n${gson.toJson(headersMap)}")
        return headersMap.toHeaders()
    }

    // == Extract object from raw JSON response ==
    @Throws(Exception::class)
    inline fun <reified T> getApiMessageObject(message: String, dummy: T): T {
        val jsonStart = message.indexOf('{')
        require(jsonStart != -1) { "Invalid message format: JSON payload not found." }

        val len = message.substring(0, jsonStart).toIntOrNull()
        require(len != null && len > 0) { "Invalid message length." }

        val payload = message.substring(jsonStart)
        val parsed: Map<String, Any> = mapper.readValue(payload, object : TypeReference<Map<String, Any>>() {})
        return mapper.convertValue(parsed, T::class.java)
    }
}