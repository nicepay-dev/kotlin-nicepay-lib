package io.github.nicepay.service.v2

import io.github.nicepay.api.v2.DirectV2Api
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.utils.NICEPay

interface ApiHttpService<T, U : DirectV2Api> {

    fun generate(data: T, request: U, config: NICEPay?): NICEPayResponseV2?

}