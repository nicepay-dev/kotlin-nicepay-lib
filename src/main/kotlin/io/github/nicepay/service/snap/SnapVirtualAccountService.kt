package io.github.nicepay.service.snap

import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapVirtualAccount
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.utils.NICEPay

interface SnapVirtualAccountService {

    @Throws(Exception::class)
    fun createVA(request: SnapVirtualAccount, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun checkStatus(request: SnapCheckStatus, accessToken: String, config: NICEPay): NICEPayResponse?

    @Throws(Exception::class)
    fun deleteVA(request: SnapCancel, accessToken: String, config: NICEPay): NICEPayResponse?
}
