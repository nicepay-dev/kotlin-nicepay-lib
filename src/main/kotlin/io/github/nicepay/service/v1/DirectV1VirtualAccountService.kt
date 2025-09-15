package io.github.nicepay.service.v1

import io.github.nicepay.data.model.DirectV1VirtualAccount
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1VirtualAccountService {

    @Throws(Exception::class)
    fun generateVirtualAccount(request: DirectV1VirtualAccount, config: NICEPay): NICEPayResponseV1?

}