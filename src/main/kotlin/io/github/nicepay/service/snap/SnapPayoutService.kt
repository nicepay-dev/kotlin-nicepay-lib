package io.github.nicepay.service.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapPayout
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.utils.NICEPay

interface SnapPayoutService {

    @Throws(Exception::class)
    fun registration(request: SnapPayout, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun approve(request: SnapPayout, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun reject(request: SnapPayout, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun balanceInquiry(request: SnapPayout, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun statusInquiry(request: SnapCheckStatus, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun cancel(request: SnapCancel, accessToken: String, config: NICEPay): NICEPayResponse?
}
