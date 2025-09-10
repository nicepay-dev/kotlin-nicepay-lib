package io.github.nicepay.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import okhttp3.ResponseBody
import retrofit2.Call

object ApiCallExecutor {

    private val print = LoggerPrint()
    private val gson = Gson()
    private val prettyGson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    fun executeCall(call: Call<NICEPayResponse>, action: String): NICEPayResponse? = try {
        val response = call.execute()

        // ---------- 1. Get raw text + parsed object ----------
        val rawText: String        // <- replaces old `result`
        val nicePayResponse: NICEPayResponse? = if (response.isSuccessful) {
            rawText = gson.toJson(response.body())
            response.body()
        } else {
            rawText = response.errorBody()?.string() ?: ""
            if (rawText.trim().startsWith("{"))
                gson.fromJson(rawText, NICEPayResponse::class.java)
            else
                NICEPayResponse()
                    .apply { responseCode =  response.code().toString()}
                    .apply { responseMessage = rawText }

        }

        // ---------- 2. Log ----------
        if (rawText.trim().startsWith("{")) {
            val pretty = prettyGson.toJson(JsonParser.parseString(rawText))
            print.logInfoResponse("Response $action : $pretty")
        } else {
            print.logInfoResponse("Response $action : $rawText")
        }

        nicePayResponse
    } catch (e: Exception) {
        e.printStackTrace()
        print.logError("Exception during $action: ${e.message}")
        null
    }


    // ------------------ Execute Call for NICEPayResponseV1 (unchanged) ------------------
    fun executeCall(call: Call<NICEPayResponseV1>, action: String): NICEPayResponseV1? {
        return try {
            val response = call.execute()
            if (!response.isSuccessful) {
                print.logError("Error during $action: ${response.errorBody()?.string()}")
            }

            val body = response.body()
            if (body == null) {
                print.logError("Response body is null during $action")
            } else {
                print.logInfoResponse(
                    "Parsed Response $action: ${
                        GsonBuilder().setPrettyPrinting().create().toJson(body)
                    }"
                )
            }

            body
        } catch (e: Exception) {
            print.logError("Exception during $action: ${e.message}")
            null
        }
    }

    // ------------------ Execute Call for HTML Response (NEW) ------------------
    fun executeHtmlCall(call: Call<ResponseBody>, action: String): String? {
        return try {
            val response = call.execute()
            val rawHtml = if (response.isSuccessful) {
                response.body()?.string()
            } else {
                response.errorBody()?.string()
            }

            if (!rawHtml.isNullOrBlank()) {
                // only log the first 200 chars to avoid spamming logs
                print.logInfoResponse("HTML Response $action : ${rawHtml.take(200)}...")
            } else {
                print.logError("Empty HTML response for $action")
            }

            rawHtml
        } catch (e: Exception) {
            print.logError("Exception during $action: ${e.message}")
            null
        }
    }

}
