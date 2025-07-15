package io.github.nicepay.service.snap.impl

import com.google.gson.Gson
import io.github.nicepay.api.snap.QrisApi
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapQris
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapQrisService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class SnapQrisServiceImpl : SnapQrisService {

    private val gson = Gson()

    override fun qrMpmGenerate(
        request: SnapQris,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            QrisApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.qrMpmGenerate(request)
        return ApiCallExecutor.executeCall(call, "qrMpmGenerate")
    }

    override fun checkStatus(
        request: SnapCheckStatus,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            QrisApi::class.java,
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
            QrisApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.refund(request)
        return ApiCallExecutor.executeCall(call, "refund")
    }


}
