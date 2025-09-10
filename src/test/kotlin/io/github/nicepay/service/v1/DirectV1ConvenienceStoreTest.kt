package io.github.nicepay.service.v1

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.PARTNER_ID
import io.github.nicepay.data.model.DirectV1ConvenienceStore
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.impl.DirectV1ConvenienceStoreServiceImpl
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class DirectV1ConvenienceStoreTest {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private lateinit var registeredData: NICEPayResponse

        private val DEFAULT_AMOUNT = "1000"
        private val DEFAULT_IMID = PARTNER_ID
        private val DEFAULT_REFERENCE_NO = "OrdNo" + TIMESTAMP
        private val IS_CLOUD_SERVER = false;
        private var I_MID = PARTNER_ID;
        private val IS_PRODUCTION = false;

        private val DEFAULT_MERCHANT_KEY = CLIENT_SECRET

        val cvsService: DirectV1ConvenienceStoreService = DirectV1ConvenienceStoreServiceImpl()
    }

    private fun generateConfig(): NICEPay {

        val config: NICEPay = NICEPay.Builder()
            .isProduction(IS_PRODUCTION)
            .isCloudServer(IS_CLOUD_SERVER)
            .partnerId(I_MID)
            .build()

        return config
    }

    @Test
    fun `Should be able to register Direct V1 Convenience Store`() {

        val config = generateConfig()

        val cvsRequest = DirectV1ConvenienceStore.Builder()
            .iMid(config.partnerId)
            .timeStamp(TIMESTAMP) // Example: Current timestamp
            .payMethod("03")
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .goodsNm("Merchant Goods 1")
            .billingNm("John Doe")
            .billingEmail("buyer@merchant.com")
            .billingPhone("081234567890")
            .billingAddr("Billing Address")
            .billingCity("Jakarta")
            .billingState("Jakarta")
            .billingPostCd("12345")
            .billingCountry("Indonesia")
            .deliveryNm("John Doe Delivery")
            .deliveryPhone("081234567890")
            .deliveryAddr("Billing Address ")
            .deliveryCity("Jakarta")
            .deliveryState("Jakarta")
            .deliveryPostCd("12345")
            .deliveryCountry("Indonesia")
            .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
            .dbProcessUrl("https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d")
            .vat("0")
            .fee("0")
            .notaxAmt("0")
            .description("Description")
            .userIP("127.0.0.1")
            .cartData("{}")
            // Note: The makeToken function is called before passing the result to the builder
            .merchantToken(config.partnerId,
                DEFAULT_REFERENCE_NO,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            // Optional fields
            .reqDt("")
            .reqTm("")
            .reqDomain("www.merchant.com")
            .reqServerIP("127.0.0.1")
            .reqClientVer("1.0")
            .userSessionID("userSessionID")
            .userAgent("Mozilla")
            .userLanguage("en-US")
            .payValidDt("")
            .payValidTm("")
            .mitraCd("ALMA")
            .build()

        val response: NICEPayResponseV1? = cvsService.registration(cvsRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
            assertEquals("SUCCESS", response.resultMsg)
        }
    }


}