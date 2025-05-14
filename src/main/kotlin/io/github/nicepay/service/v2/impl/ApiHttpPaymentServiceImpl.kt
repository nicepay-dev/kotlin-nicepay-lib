package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.DirectV2ApiPayment
import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet
import io.github.nicepay.service.v2.ApiHttpPaymentService
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import okhttp3.ResponseBody
import org.apache.logging.log4j.util.Strings
import retrofit2.Call
import retrofit2.Response

class ApiHttpPaymentServiceImpl<T : DirectV2RequestPaymentEwallet> : ApiHttpPaymentService<T> {

    private val print: LoggerPrint = LoggerPrint()

    override fun generate(data: T, config: NICEPay?): String? {
        val callSync : Call<ResponseBody?> = ApiUtils.createServiceV2(DirectV2ApiPayment::class.java, config!!)
            .hitDirectV2ApiPayment(data.dataMap())!!
        val response : Response<ResponseBody?>
        var nicepayResponse : ResponseBody?
        var errorResponse: ResponseBody? = null
        var resClient: String? = ""

        try {
            response = callSync.execute()
            nicepayResponse = response.body()
            print.logInfoV2("Response Hit API : " + nicepayResponse)

            response.errorBody().also {
                if (it != null) {
                    errorResponse = it
                }
            }

            resClient = nicepayResponse?.string() ?: errorResponse!!.string()

            if (Strings.isNotBlank(errorResponse.toString())) {
                print.logError("error message = " + errorResponse.toString())
            }

            print.logInfo(
                LoggerPrint.LOG_RESPONSE + "Response Payment Request :$resClient"
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return resClient
    }

}