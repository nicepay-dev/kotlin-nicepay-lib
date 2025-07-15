package io.github.nicepay.service.snap.impl

import com.google.gson.Gson
import io.github.nicepay.api.snap.EwalletApi
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapEwallet
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapEwalletService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class SnapEwalletServiceImpl : SnapEwalletService {

    private val gson = Gson()

    override fun paymentHostToHost(
        request: SnapEwallet,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            EwalletApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.paymentHostToHost(request)

        return ApiCallExecutor.executeCall(call, "paymentHostToHost")
    }

    override fun checkStatus(
        request: SnapCheckStatus,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            EwalletApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.status(request)
        return ApiCallExecutor.executeCall(call, "checkStatus")
    }

    override fun refund(
        request: SnapCancel,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            EwalletApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.refund(request)
        return ApiCallExecutor.executeCall(call, "refund")
    }


}
