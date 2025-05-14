package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.model.DirectV2RequestPaymentCard
import io.github.nicepay.service.v2.ApiHttpPaymentService
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.utils.NICEPay

class DirectV2PaymentCardServiceImpl : DirectV2PaymentService<DirectV2RequestPaymentCard> {

    override fun registration(data: DirectV2RequestPaymentCard, config: NICEPay?): String? {
        var nicePayResponse: String? = null
        val apiHttpService : ApiHttpPaymentService<DirectV2RequestPaymentCard> = ApiHttpPaymentServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}