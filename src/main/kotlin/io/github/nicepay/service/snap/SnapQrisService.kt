package io.github.nicepay.service.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapQris
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.utils.NICEPay

interface SnapQrisService {

    @Throws(Exception::class)
    fun qrMpmGenerate(request: SnapQris, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun checkStatus(request: SnapCheckStatus, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun refund(request: SnapCancel, accessToken: String, config: NICEPay): NICEPayResponse?
}
