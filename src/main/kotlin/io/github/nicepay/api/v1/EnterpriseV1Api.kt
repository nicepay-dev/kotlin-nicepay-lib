package io.github.nicepay.api.v1

import io.github.nicepay.data.response.v1.NICEPayResponseV1
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EnterpriseV1Api {

//    Common

    @FormUrlEncoded
    @POST("nicepay/api/onePass.do")
    fun onePass(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/onePassStatus.do")
    fun onePassStatus(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/onePassAllCancel.do")
    fun onePassAllCancel(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>


}