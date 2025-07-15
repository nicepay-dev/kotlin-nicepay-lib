package io.github.nicepay.api.snap

import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.response.snap.NICEPayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessTokenApi {

    @POST("nicepay/v1.0/access-token/b2b")
    fun requestAccessToken(@Body request: AccessToken?): Call<NICEPayResponse>

}