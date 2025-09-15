// In file: io/github/nicepay/service/v1/impl/RedirectV1ServiceImpl.kt

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.github.nicepay.api.v1.EnterpriseV1Api
import io.github.nicepay.api.v1.EnterpriseV1CardApi
import io.github.nicepay.data.model.DirectV1Card
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.DirectV1CardService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import okhttp3.ResponseBody
import retrofit2.Call
import java.net.URLEncoder

class DirectV1CardServiceImpl : DirectV1CardService {

    private val gson = Gson()

//    override fun registration(request: RedirectV1, config: NICEPay): NICEPayResponseV1? {

//    }

    private fun convertToMap(obj: Any): Map<String, String> {
        val json = gson.toJson(obj)
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }

    fun toNestedJson(obj: Any): JsonObject {
        val jsonElement: JsonElement = gson.toJsonTree(obj) // turn model into JSON tree
        val wrapper = JsonObject()
        wrapper.add("jsonData", jsonElement) // put model JSON under "jsonData"
        return wrapper
    }

    override fun requestToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )
        val jsonString = gson.toJson(request)
        val encodedJson = URLEncoder.encode(jsonString, "UTF-8")

//        val requestBody = formBody.toRequestBody("application/x-www-form-urlencoded".toMediaType())

        val call: Call<NICEPayResponseV1> = service.onePassToken(encodedJson)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD")
    }


    override fun paymentCard3DS(request: DirectV1Card, config: NICEPay): String? {

        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        val requestMap: Map<String, String> = convertToMap(request)
        val call: Call<ResponseBody> = service.secureVeRequest(requestMap)
        return ApiCallExecutor.executeHtmlCall(call, "DIRECT_V1_CARD_3DS")

    }

    override fun paymentCardMigs(request: DirectV1Card, config: NICEPay): String? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        val requestMap: Map<String, String> = convertToMap(request)
        val call: Call<ResponseBody> = service.migsRequest(requestMap)
        return ApiCallExecutor.executeHtmlCall(call, "DIRECT_V1_CARD_MIGS")
    }

    override fun paymentCard(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1Api::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.onePass(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_PAYMENT")
    }

    override fun recurringIssue(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )
        val jsonString = gson.toJson(request)
        val encodedJson = URLEncoder.encode(jsonString, "UTF-8")

//        val requestBody = formBody.toRequestBody("application/x-www-form-urlencoded".toMediaType())

        val call: Call<NICEPayResponseV1> = service.recurringToken(encodedJson)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_ISSUE_RECURRING")
    }

    override fun recurringTrans(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.recurringTrans(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_PAYMENT_RECURRING")
    }

    override fun tokenizedRecurringCard(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.tokenize(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_TOKENIZED_RECURRING")
    }

    override fun checkToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.checkToken(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_CHECK_TOKEN")
    }

    override fun removeToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.removeToken(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_REMOVE_TOKEN")
    }

    override fun capturePreAuth(request: DirectV1Card, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1CardApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.captureTrans(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_CARD_CAPTURE_PRE_AUTH")
    }
}