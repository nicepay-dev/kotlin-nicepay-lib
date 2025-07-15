package io.github.nicepay.api.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapVirtualAccount
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST

interface VirtualAccountApi {

    @POST("nicepay/api/v1.0/transfer-va/create-va")
    fun createVA(@Body request: SnapVirtualAccount?): Call<NICEPayResponse>

    @POST("nicepay/api/v1.0/transfer-va/status")
    fun status(@Body request: SnapCheckStatus?): Call<NICEPayResponse>

    @HTTP(method = "DELETE", path = "nicepay/api/v1.0/transfer-va/delete-va", hasBody = true)
    fun deleteVA(@Body request: SnapCancel?): Call<NICEPayResponse>

}