package io.github.nicepay.service.v1

import io.github.nicepay.data.model.DirectV1ConvenienceStore
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1ConvenienceStoreService {

    @Throws(Exception::class)
    fun registration(request: DirectV1ConvenienceStore, config: NICEPay): NICEPayResponseV1?

}