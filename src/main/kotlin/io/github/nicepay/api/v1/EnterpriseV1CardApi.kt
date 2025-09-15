package io.github.nicepay.api.v1

import io.github.nicepay.data.response.v1.NICEPayResponseV1
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EnterpriseV1CardApi {

    //    CC
    @FormUrlEncoded
    @POST("nicepay/api/onePassToken.do")
    fun onePassToken(
        @Field("jsonData", encoded = true) jsonData: String
    ): Call<NICEPayResponseV1>


    @FormUrlEncoded
    @POST("nicepay/api/secureVeRequest.do")
    fun secureVeRequest(@FieldMap fields: Map<String, String>?): Call<ResponseBody>

    @FormUrlEncoded
    @POST("nicepay/api/migsRequest.do")
    fun migsRequest(@FieldMap fields: Map<String, String>?): Call<ResponseBody>

    @FormUrlEncoded
    @POST("nicepay/api/recurringToken.do")
    fun recurringToken(
        @Field("jsonData", encoded = true) jsonData: String
    ): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/recurringTrans.do")
    fun recurringTrans(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/tokenize.do")
    fun tokenize(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/checkToken.do")
    fun checkToken(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/removeToken.do")
    fun removeToken(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>

    @FormUrlEncoded
    @POST("nicepay/api/captureTrans.do")
    fun captureTrans(@FieldMap fields: Map<String, String>?): Call<NICEPayResponseV1>



}