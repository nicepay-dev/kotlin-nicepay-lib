package io.github.nicepay.service.snap

import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.utils.NICEPay

interface SnapAccessTokenService {

    @Throws(Exception::class)
    fun getAccessToken(request: AccessToken, config: NICEPay): NICEPayResponse?
}