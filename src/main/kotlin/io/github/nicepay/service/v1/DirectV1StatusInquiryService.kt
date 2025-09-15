package io.github.nicepay.service.v1

import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1StatusInquiryService {

    @Throws(Exception::class)
    fun statusInquiry(request: DirectV1StatusInquiry, config: NICEPay): NICEPayResponseV1?

}