package io.github.nicepay.service.v1

import io.github.nicepay.data.model.DirectV1DirectDebit
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1DirectDebitService {

    @Throws(Exception::class)
    fun registration(request: DirectV1DirectDebit, config: NICEPay): NICEPayResponseV1?

}