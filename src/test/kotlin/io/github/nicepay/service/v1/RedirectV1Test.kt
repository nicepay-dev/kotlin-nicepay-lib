package io.github.nicepay.service.v1

import RedirectV1ServiceImpl
import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.I_MID_NORMALTEST
import io.github.nicepay.data.model.RedirectV1
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class RedirectV1Test {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private lateinit var registeredData: NICEPayResponse

        private val DEFAULT_AMOUNT = "10000"
        private val DEFAULT_REFERENCE_NO = "kotlinRedV1" + TIMESTAMP
        private val IS_CLOUD_SERVER = false;
        private val IS_PRODUCTION = false;

        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
        private val DEFAULT_IMID = "IONPAYTEST"


    }

    @Test
    fun `Should be able to register Redirect V1 Transaction`() {

        val redirectV1Service: RedirectV1Service = RedirectV1ServiceImpl()
        val config = generateConfig()

        val redirectV1 = RedirectV1.Builder()
            .iMid(config.partnerId)
            .timeStamp(TIMESTAMP) // Example: Current timestamp
            .payMethod("01")
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
            .shopId("NICEPAY")
            .instmntMon("1")
            .instmntType("1")
            .recurrOpt("0")
            .cartData("{}")
            // Note: The makeToken function is called before passing the result to the builder
            .merchantToken(config.partnerId, DEFAULT_REFERENCE_NO, DEFAULT_AMOUNT,DEFAULT_MERCHANT_KEY)

            // Optional fields
            .reqDt("20160301")
            .reqTm("135959")
            .reqDomain("www.merchant.com")
            .reqServerIP("127.0.0.1")
            .reqClientVer("1.0")
            .userSessionID("userSessionID")
            .userAgent("Mozilla")
            .userLanguage("en-US")
            .merFixAcctId("9999000000000001")
            .vacctValidDt("20160303")
            .vacctValidTm("135959")
            .paymentExpiryDt("20160303")
            .paymentExpiryTm("135959")
            .build()

        val response: NICEPayResponseV1? = redirectV1Service.registration(redirectV1, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.data?.resultCd)
            assertEquals("SUCCESS", response.data?.resultMsg)
        }
    }

    private fun generateConfig(): NICEPay {

        val config: NICEPay = NICEPay.Builder()
            .isProduction(IS_PRODUCTION)
            .isCloudServer(IS_CLOUD_SERVER)
            .partnerId(I_MID_NORMALTEST)
            .build()

        return config
    }
}