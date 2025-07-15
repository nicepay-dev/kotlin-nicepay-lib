package io.github.nicepay.service.snap.impl
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.EXTERNAL_ID
import io.github.nicepay.data.TestingConstants.Companion.I_MID_NORMALTEST
import io.github.nicepay.data.TestingConstants.Companion.NORMALTEST_CLOUD_PRIVATE_KEY
import io.github.nicepay.data.TestingConstants.Companion.PRIVATE_KEY
import io.github.nicepay.data.TestingConstants.Companion.TIMESTAMP
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SnapAccessTokenTest {

    companion object {

        var config: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(CLIENT_SECRET)
            .partnerId(I_MID_NORMALTEST)
            .externalID(EXTERNAL_ID)
            .timestamp(TIMESTAMP)
            .privateKey(PRIVATE_KEY)
            .build()

        private val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(CLIENT_SECRET)
            .partnerId(I_MID_NORMALTEST)
            .externalID(EXTERNAL_ID)
            .timestamp(TIMESTAMP)
            .privateKey(NORMALTEST_CLOUD_PRIVATE_KEY)
            .build()

        lateinit var registeredData : NICEPayResponse

    }


    @Test
    fun `Should get access token successfully from Nicepay`() {
        val service: SnapAccessTokenService = SnapAccessTokenServiceImpl()

        val request = AccessToken.Builder()
            .grantType("client_credentials")
            .additionalInfo(emptyMap())
            .build()

        val response = service.getAccessToken(request, configCloud)

        assertNotNull(response)

        if (response != null) {
            assertNotNull(response.accessToken)
            assertEquals("Successful", response.responseMessage)
            assertEquals("2007300", response.responseCode)
        }
    }






}