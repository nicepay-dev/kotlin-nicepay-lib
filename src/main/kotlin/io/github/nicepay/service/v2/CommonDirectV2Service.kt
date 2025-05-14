package io.github.nicepay.service.v2

import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.utils.NICEPay

interface CommonDirectV2Service {

    fun checkStatus(data: DirectV2CheckStatus, config: NICEPay?): NICEPayResponseV2?

    fun cancel(data: DirectV2Cancel, config: NICEPay?): NICEPayResponseV2?

}