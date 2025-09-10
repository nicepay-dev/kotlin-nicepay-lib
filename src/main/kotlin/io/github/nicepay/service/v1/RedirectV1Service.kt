package io.github.nicepay.service.v1

import io.github.nicepay.data.model.RedirectV1
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface RedirectV1Service {

    @Throws(Exception::class)
    fun registration(request: RedirectV1, config: NICEPay): NICEPayResponseV1?


}