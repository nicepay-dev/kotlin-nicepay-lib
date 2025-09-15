package io.github.nicepay.service.v1

import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1EwalletService {

    @Throws(Exception::class)
    fun registration(request: DirectV1Ewallet, config: NICEPay): NICEPayResponseV1?

}