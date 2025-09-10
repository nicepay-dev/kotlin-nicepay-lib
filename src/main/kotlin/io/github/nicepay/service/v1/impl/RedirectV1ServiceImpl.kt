// In file: io/github/nicepay/service/v1/impl/RedirectV1ServiceImpl.kt

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.nicepay.api.v1.ProfessionalV1Api
import io.github.nicepay.data.model.RedirectV1
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.RedirectV1Service
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class RedirectV1ServiceImpl : RedirectV1Service {

    private val gson = Gson()

    override fun registration(request: RedirectV1, config: NICEPay): NICEPayResponseV1? {
        val service = ApiUtils.createServiceV1(
            ProfessionalV1Api::class.java,
            config
        )

        // Convert the RedirectV1 object to a Map<String, String>
        val requestMap: Map<String, String> = convertToMap(request)

        val call: Call<NICEPayResponseV1> = service.orderRegist(requestMap)
        return ApiCallExecutor.executeCall(call, "REDIRECT_V1")
    }

    private fun convertToMap(obj: Any): Map<String, String> {
        val json = gson.toJson(obj)
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }
}