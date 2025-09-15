package io.github.nicepay.api.v1

import io.github.nicepay.data.response.v1.NICEPayResponseV1
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProfessionalV1Api {

    @FormUrlEncoded
    @POST("nicepay/api/orderRegist.do")
    fun orderRegist(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>
}