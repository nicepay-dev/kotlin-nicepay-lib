package io.github.nicepay.api.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapEwallet
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EwalletApi {

    @POST("nicepay/api/v1.0/debit/payment-host-to-host")
    fun paymentHostToHost(@Body request: SnapEwallet?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/debit/status")
    fun status(@Body request: SnapCheckStatus?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/debit/refund")
    fun refund(@Body request: SnapCancel?): Call<NICEPayResponse>

}