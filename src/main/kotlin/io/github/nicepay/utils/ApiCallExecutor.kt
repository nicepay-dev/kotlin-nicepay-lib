package io.github.nicepay.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call

object ApiCallExecutor {

    private val gson = Gson()
    private val prettyGson = GsonBuilder().setPrettyPrinting().create()
    private val print = LoggerPrint()

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

}
