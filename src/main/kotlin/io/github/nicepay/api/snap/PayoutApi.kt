package io.github.nicepay.api.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapPayout
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PayoutApi {

    @POST("nicepay/api/v1.0/transfer/registration")
    fun registration(@Body request: SnapPayout?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/transfer/approve")
    fun approve(@Body request: SnapPayout?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/transfer/reject")
    fun reject(@Body request: SnapPayout?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/transfer/cancel")
    fun cancel(@Body request: SnapCancel?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/transfer/inquiry")
    fun statusInquiry(@Body request: SnapCheckStatus?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/balance-inquiry")
    fun balanceInquiry(@Body request: SnapPayout?): Call<NICEPayResponse>

}