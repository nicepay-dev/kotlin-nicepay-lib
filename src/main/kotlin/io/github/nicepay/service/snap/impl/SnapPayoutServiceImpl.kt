package io.github.nicepay.service.snap.impl

import com.google.gson.Gson
import io.github.nicepay.api.snap.PayoutApi
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapPayout
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapPayoutService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class SnapPayoutServiceImpl : SnapPayoutService {

    private val gson = Gson()

    override fun registration(
        request: SnapPayout,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.registration(request)
        return ApiCallExecutor.executeCall(call, "registration")
    }

    override fun approve(
        request: SnapPayout,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.approve(request)
        return ApiCallExecutor.executeCall(call, "approve")
    }

    override fun reject(
        request: SnapPayout,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.reject(request)
        return ApiCallExecutor.executeCall(call, "reject")
    }

    override fun balanceInquiry(
        request: SnapPayout,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.balanceInquiry(request)
        return ApiCallExecutor.executeCall(call, "balanceInquiry")
    }

    override fun statusInquiry(
        request: SnapCheckStatus,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.statusInquiry(request)
        return ApiCallExecutor.executeCall(call, "statusInquiry")
    }

    override fun cancel(
        request: SnapCancel,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            PayoutApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.cancel(request)
        return ApiCallExecutor.executeCall(call, "cancel")
    }

}
