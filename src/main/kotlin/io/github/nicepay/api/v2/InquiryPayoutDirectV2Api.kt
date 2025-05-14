package io.github.nicepay.api.v2

import io.github.nicepay.data.response.v2.NICEPayResponseV2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InquiryPayoutDirectV2Api : DirectV2Api {

    @POST("nicepay/api/direct/v2/inquiryPayout")
    override fun hitDirectV2Api(@Body request: Any?): Call<NICEPayResponseV2?>?

}