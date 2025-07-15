package io.github.nicepay.api.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapQris
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface QrisApi {

    @POST("nicepay/api/v1.0/qr/qr-mpm-generate")
    fun qrMpmGenerate(@Body request: SnapQris?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/qr/qr-mpm-query")
    fun status(@Body request: SnapCheckStatus?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/qr/qr-mpm-refund")
    fun refund(@Body request: SnapCancel?): Call<NICEPayResponse>

}