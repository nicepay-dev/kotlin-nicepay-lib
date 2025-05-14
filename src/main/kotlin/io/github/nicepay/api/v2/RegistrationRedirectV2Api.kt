package io.github.nicepay.api.v2

import io.github.nicepay.data.response.v2.NICEPayResponseV2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationRedirectV2Api : DirectV2Api {

    @POST("nicepay/redirect/v2/registration")
    override fun hitDirectV2Api(@Body request: Any?): Call<NICEPayResponseV2?>?

}