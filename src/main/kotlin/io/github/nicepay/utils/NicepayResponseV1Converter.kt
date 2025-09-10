package io.github.nicepay.retrofit.converter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class NicepayResponseV1Converter private constructor(private val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): NicepayResponseV1Converter {
            return NicepayResponseV1Converter(GsonBuilder().create())
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return ResponseBodyConverter(gson, type)
    }

    private class ResponseBodyConverter(
        private val gson: Gson,
        private val type: Type
    ) : Converter<ResponseBody, Any> {

        override fun convert(body: ResponseBody): Any {
            var raw = body.string().trim()
            body.close()

            // 🔹 Handle Nicepay’s wrapping quirks
            if (raw.length > 4 && raw.take(4).all { it.isDigit() }) {
                raw = raw.drop(4).trim()
            }
            if (raw.startsWith("(") && raw.endsWith(")")) {
                raw = raw.substring(1, raw.length - 1).trim()
            }

            // 🔹 Correctly map into expected model type
            return gson.fromJson(raw, type)
        }
    }
}
