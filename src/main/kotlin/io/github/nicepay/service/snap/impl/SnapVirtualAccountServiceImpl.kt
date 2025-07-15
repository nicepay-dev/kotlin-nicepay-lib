package io.github.nicepay.service.snap.impl

import com.google.gson.Gson
import io.github.nicepay.api.snap.VirtualAccountApi
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapVirtualAccount
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapVirtualAccountService
import io.github.nicepay.utils.ApiCallExecutor
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import retrofit2.Call

class SnapVirtualAccountServiceImpl : SnapVirtualAccountService {

    private val gson = Gson()

    override fun createVA(
        request: SnapVirtualAccount,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            VirtualAccountApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.createVA(request)
        return ApiCallExecutor.executeCall(call, "createVA")
    }

    override fun checkStatus(
        request: SnapCheckStatus,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            VirtualAccountApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.status(request)
        return ApiCallExecutor.executeCall(call, "checkStatus")
    }

    override fun deleteVA(
        request: SnapCancel,
        accessToken: String,
        config: NICEPay
    ): NICEPayResponse? {
        val service = ApiUtils.createService(
            VirtualAccountApi::class.java,
            AccessToken.Builder().build().getGrantType(),
            accessToken,
            gson.toJson(request),
            config
        )
        val call: Call<NICEPayResponse> = service.deleteVA(request)
        return ApiCallExecutor.executeCall(call, "deleteVA")
    }
}
