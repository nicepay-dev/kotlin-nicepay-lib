package io.github.nicepay.service.v1

import io.github.nicepay.data.model.DirectV1Qris
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1QrisService {

    @Throws(Exception::class)
    fun registration(request: DirectV1Qris, config: NICEPay): NICEPayResponseV1?

}