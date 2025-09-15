package io.github.nicepay.service.v1.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.nicepay.api.v1.EnterpriseV1EwalletApi
import io.github.nicepay.data.model.DirectV1Ewallet
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.DirectV1EwalletService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class DirectV1EwalletServiceImpl : DirectV1EwalletService {

    private val gson = Gson()

    override fun registration(request: DirectV1Ewallet, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1EwalletApi::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.ewalletTrans(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_EWALLET")
    }

    private fun convertToMap(obj: Any): Map<String, String> {
        val json = gson.toJson(obj)
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }
}