package io.github.nicepay.service.v2.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.github.nicepay.api.v2.DirectV2Api
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.ApiHttpService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ApiHttpServiceImpl<T, U : DirectV2Api> : ApiHttpService<T, U> {

    private val print: LoggerPrint = LoggerPrint()

    override fun generate(data: T, request: U, config: NICEPay?): NICEPayResponseV2? {
        val gson = Gson()

        val callSync: Call<NICEPayResponseV2?> = request.hitDirectV2Api(data)!!
        val response: Response<NICEPayResponseV2?>
        var nicePayResponse: NICEPayResponseV2? = null
        var errorResponse: ResponseBody? = null
        val resClient: Any
        val jsonObject: JsonObject
        try {
            response = callSync.execute()
            nicePayResponse = response.body()
            response.errorBody().also {
                if (it != null) {
                    errorResponse = it
                }
            }

            resClient = if (nicePayResponse == null) {
                errorResponse.toString()
            } else {
                gson.toJson(nicePayResponse)
            }

            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject()
            print.logInfoResponseV2("Response Hit API :" + GsonBuilder().setPrettyPrinting().create().toJson(jsonObject))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}