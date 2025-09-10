package io.github.nicepay.service.v1.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.nicepay.api.v1.EnterpriseV1Api
import io.github.nicepay.data.model.DirectV1StatusInquiry
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.DirectV1StatusInquiryService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class DirectV1StatusInquiryServiceImpl : DirectV1StatusInquiryService {

    private val gson = Gson()

    override fun statusInquiry(request: DirectV1StatusInquiry, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            EnterpriseV1Api::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.onePassStatus(requestMap)
        return ApiCallExecutor.executeCall(call, "DIRECT_V1_STATUS_INQUIRY")
    }

    private fun convertToMap(obj: Any): Map<String, String> {
        val json = gson.toJson(obj)
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }
}