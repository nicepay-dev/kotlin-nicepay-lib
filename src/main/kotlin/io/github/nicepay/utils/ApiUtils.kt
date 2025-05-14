package io.github.nicepay.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

class ApiUtils {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val mapper: ObjectMapper = ObjectMapper()

        private val builder
                : Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

        private var api: Retrofit? = null

        private val httpClient
                : OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(Duration.ofMillis(10000))

        var httpClientTimeout
                : OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MILLISECONDS) // Set a very low connection timeout
            .readTimeout(1, TimeUnit.MILLISECONDS) // Set a very low read timeout
            .writeTimeout(1, TimeUnit.MILLISECONDS)

        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        fun <S> createService(
            serviceClass: Class<S>,
            grandType: String?,
            accessToken: String,
            data: String,
            config: NICEPay
        ): S {
            httpClient.interceptors().clear()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                var builder: Request.Builder? = null
                print.logInfo("generate " + "fullUrl :" + chain.request().url)
                val url = chain.request().url.encodedPath.replace("/nicepay", "")
                Optional.ofNullable(grandType)
                    .ifPresentOrElse(
                        { value: String? -> print.logInfo("getToken pathUrl :$url") },
                        { print.logInfo("generate pathUrl :$url") }
                    )

                try {
                    val httpMethod = original.method
                    builder = original.newBuilder()
                        .headers(getHeaders(httpMethod, grandType, accessToken, data, url, config))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val response = chain.proceed(builder!!.build())
                val bodyString = response.body!!.string()
                val contentType = response.body!!.contentType()
                val responseBody: ResponseBody = ResponseBody.create(contentType, bodyString)
                print.logInfoBody(
                    "Request Data " + GsonBuilder().setPrettyPrinting().create().toJson(
                        original.tag(
                            Invocation::class.java
                        )!!.arguments()[0]
                    )
                )
                response.newBuilder().body(responseBody).build()
            }
            builder.client(httpClient.build())
            builder.baseUrl(config.getNICEPayBaseUrl())
            api = builder.build()

            return api!!.create(serviceClass)
        }

        fun <S> createServiceConfig(
            serviceClass: Class<S>,
            grandType: String?,
            accessToken: String,
            data: String,
            config: NICEPay
        ): S {
            httpClient.interceptors().clear()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                var builder: Request.Builder? = null
                print.logInfo("generate " + "fullUrl :" + chain.request().url)
                val url = chain.request().url.encodedPath.replace("/nicepay", "")
                Optional.ofNullable(grandType)
                    .ifPresentOrElse(
                        { value: String? -> print.logInfo("getToken pathUrl :$url") },
                        { print.logInfo("generate pathUrl :$url") }
                    )

                try {
                    val httpMethod = original.method
                    builder = original.newBuilder()
                        .headers(getHeaders(httpMethod, grandType, accessToken, data, url, config))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val response = chain.proceed(builder!!.build())
                val bodyString = response.body!!.string()
                val contentType = response.body!!.contentType()
                val responseBody: ResponseBody = ResponseBody.create(contentType, bodyString)
                print.logInfoBody(
                    "Request Data " + GsonBuilder().setPrettyPrinting().create().toJson(
                        original.tag(
                            Invocation::class.java
                        )!!.arguments()[0]
                    )
                )
                response.newBuilder().body(responseBody).build()
            }
            builder.client(httpClient.build())
            api = builder.build()

            return api!!.create(serviceClass)
        }

        fun <S> createTimeoutService(
            serviceClass: Class<S>,
            grandType: String?,
            accessToken: String,
            data: String,
            config: NICEPay
        ): S {
            httpClientTimeout.interceptors().clear()
            httpClientTimeout.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                var builder: Request.Builder? = null
                print.logInfo("generate " + "fullUrl :" + chain.request().url)
                val url = chain.request().url.encodedPath.replace("/nicepay", "")
                Optional.ofNullable(grandType)
                    .ifPresentOrElse(
                        { value: String? -> print.logInfo("getToken pathUrl :$url") },
                        { print.logInfo("generate pathUrl :$url") }
                    )

                try {
                    val httpMethod = original.method
                    builder = original.newBuilder()
                        .headers(getHeaders(httpMethod, grandType, accessToken, data, url, config))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val response = chain.proceed(builder!!.build())
                val bodyString = response.body!!.string()
                val contentType = response.body!!.contentType()
                val responseBody: ResponseBody = ResponseBody.create(contentType, bodyString)
                print.logInfoBody(
                    "Request Data Timeout" + GsonBuilder().setPrettyPrinting().create().toJson(
                        original.tag(
                            Invocation::class.java
                        )!!.arguments()[0]
                    )
                )
                response.newBuilder().body(responseBody).build()
            }
            builder.client(httpClientTimeout.build())
            api = builder.build()

            return api!!.create(serviceClass)
        }

        //Get Header
        @Throws(Exception::class)
        private fun getHeaders(
            httpMethod: String,
            grandType: String?,
            accessToken: String,
            data: String,
            pathUrl: String,
            config: NICEPay
        ): Headers {
            val headersMap: MutableMap<String, String> = HashMap()
            val partnerID: String = config.partnerId.toString()
            val privateKey: String = config.privateKey.toString()
            val secretKey: String = config.clientSecret.toString()
            val externalID: String = config.externalID.toString()
            val timeStamp: String = config.timestamp.toString()

            headersMap["Content-Type"] = "application/json"
            if (grandType != null) {
                val stringToSign = "$partnerID|$timeStamp"
                val signatureAccessToken: String = SignatureUtils.signSHA256RSA(stringToSign, privateKey)
                headersMap["X-TIMESTAMP"] = timeStamp
                headersMap["X-CLIENT-KEY"] = partnerID
                headersMap["X-SIGNATURE"] = signatureAccessToken
            } else {
                val hashData: String = SignatureUtils.sha256EncodeHex(data)
                val signature: String =
                    SignatureUtils.getSignature(httpMethod, accessToken, hashData, pathUrl, timeStamp, secretKey)
                headersMap["Authorization"] = "Bearer $accessToken"
                headersMap["X-TIMESTAMP"] = timeStamp
                headersMap["X-SIGNATURE"] = signature
                headersMap["X-PARTNER-ID"] = partnerID
                headersMap["X-EXTERNAL-ID"] = externalID
                headersMap["CHANNEL-ID"] = partnerID + "01"
            }
            print.logInfoHeader("Request Header " + GsonBuilder().setPrettyPrinting().create().toJson(headersMap))

            return headersMap.toHeaders()
        }

        //V2
        fun <S> createServiceV2(serviceClass: Class<S>, config: NICEPay): S {
            httpClient.interceptors().clear()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                var builder: Request.Builder? = null
                print.logInfoV2("generate " + "fullUrl :" + chain.request().url)

                try {
                    builder = original.newBuilder()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val response = chain.proceed(builder!!.build())
                val bodyString = response.body!!.string()
                val contentType = response.body!!.contentType()
                val responseBody: ResponseBody = ResponseBody.create(contentType, bodyString)
                print.logInfoBodyV2(
                    "Request Data " + GsonBuilder().setPrettyPrinting().create().toJson(
                        Objects.requireNonNull(
                            original.tag(
                                Invocation::class.java
                            )
                        )?.arguments()
                    )
                )
                response.newBuilder().body(responseBody).build()
            }
            builder.client(httpClient.build())
            builder.baseUrl(config.getNICEPayBaseUrl())
            api = builder.build()

            return api!!.create(serviceClass)
        }

        //V2
        fun <S> createServiceV1(serviceClass: Class<S>, config: NICEPay): S {
            httpClient.interceptors().clear()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                var builder: Request.Builder? = null
                print.logInfoV1("generate " + "fullUrl :" + chain.request().url)

                try {
                    builder = original.newBuilder()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val response = chain.proceed(builder!!.build())
                val bodyString = response.body!!.string()
                val contentType = response.body!!.contentType()
                val responseBody: ResponseBody = ResponseBody.create(contentType, bodyString)
                print.logInfoBodyV1(
                    "Request Data " + GsonBuilder().setPrettyPrinting().create().toJson(
                        original.tag(
                            Invocation::class.java
                        )!!.arguments()[0]
                    )
                )
                response.newBuilder().body(responseBody).build()
            }

            builder.client(httpClient.build())
            builder.baseUrl(config.getNICEPayBaseUrl())
            api = builder.build()

            return api!!.create(serviceClass)
        }

        @Throws(Exception::class)
        private inline fun <reified T> getApiMessageObject(message: String, `object`: T): T {
            try {
                // Determine where the JSON payload starts
                val jsonStartIndex = message.indexOf("{")
                require(jsonStartIndex != -1) { "Invalid message format: JSON payload not found." }

                val messageLen = message.substring(0, jsonStartIndex).toInt()
                require(messageLen > 0) { "Invalid message length." }

                val jsonPayload = message.substring(jsonStartIndex)
                val parsedMap: Map<String, Any> = mapper.readValue(
                    jsonPayload,
                    object : TypeReference<Map<String, Any>>() {}
                )

                return mapper.convertValue<T>(parsedMap, T::class.java)
            } catch (e: Exception) {
                throw Exception("Failed to parse API message: " + e.message, e)
            }
        }
    }

}