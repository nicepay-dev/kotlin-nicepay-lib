package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet
import io.github.nicepay.service.v2.ApiHttpPaymentService
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.utils.NICEPay

class DirectV2PaymentEwalletServiceImpl : DirectV2PaymentService<DirectV2RequestPaymentEwallet> {

    override fun registration(data: DirectV2RequestPaymentEwallet, config: NICEPay?): String? {
        var nicePayResponse: String? = null
        val apiHttpService : ApiHttpPaymentService<DirectV2RequestPaymentEwallet> = ApiHttpPaymentServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}