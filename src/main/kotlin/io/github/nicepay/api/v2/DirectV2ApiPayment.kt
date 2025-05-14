package io.github.nicepay.api.v2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DirectV2ApiPayment {

    @FormUrlEncoded
    @POST("nicepay/direct/v2/payment")
    fun hitDirectV2ApiPayment(@FieldMap fields: Map<String, String>?): Call<ResponseBody?>?

}