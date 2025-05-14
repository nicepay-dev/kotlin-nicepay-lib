package io.github.nicepay.service.v2

import io.github.nicepay.api.v2.DirectV2ApiPayment
import io.github.nicepay.utils.NICEPay

interface ApiHttpPaymentService<T> {

    fun generate(data: T, config: NICEPay?): String?

}