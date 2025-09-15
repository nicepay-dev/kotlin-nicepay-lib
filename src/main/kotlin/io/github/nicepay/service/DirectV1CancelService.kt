package io.github.nicepay.service

import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1CancelService {

    @Throws(Exception::class)
    fun cancel(request: DirectV1Cancel, config: NICEPay): NICEPayResponseV1?

}