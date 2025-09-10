package io.github.nicepay.service.v1

import io.github.nicepay.data.model.DirectV1Card
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay

interface DirectV1CardService {

    @Throws(Exception::class)
    fun requestToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun paymentCard3DS(request: DirectV1Card, config: NICEPay): String?

    @Throws(Exception::class)
    fun paymentCardMigs(request: DirectV1Card, config: NICEPay): String?

    @Throws(Exception::class)
    fun paymentCard(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun recurringIssue(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun recurringTrans(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun tokenizedRecurringCard(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun checkToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun removeToken(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?

    @Throws(Exception::class)
    fun capturePreAuth(request: DirectV1Card, config: NICEPay): NICEPayResponseV1?



}