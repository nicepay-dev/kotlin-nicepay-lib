package io.github.nicepay.service.v1

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.model.DirectV1Ewallet
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.impl.DirectV1EwalletServiceImpl
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class DirectV1EwalletTest {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private val DEFAULT_AMOUNT = "10000"
        private val DEFAULT_REFERENCE_NO = "OrdNo"+ TIMESTAMP
        private val IS_CLOUD_SERVER = false;
        private var I_MID = "IONPAYTEST";
        private val IS_PRODUCTION = false;

        private val DEFAULT_MERCHANT_KEY = CLIENT_SECRET

        val ewalletService: DirectV1EwalletService = DirectV1EwalletServiceImpl()
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
    fun `Should be able to register Direct V1 EWALLET`() {

        val config = generateConfig()

        val ewalletReq = DirectV1Ewallet.Builder()
            .iMid(config.partnerId)
            .timeStamp(TIMESTAMP) // Example: Current timestamp
            .payMethod("05")
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
            .cartData("{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"$DEFAULT_AMOUNT\",\"goods_quantity\":\"1\"}]}")
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
            .mitraCd("ESHP")
            .returnJsonFormat("1")
            .paymentExpDt("")
            .paymentExpTm("")
            .build()

        val response: NICEPayResponseV1? = ewalletService.registration(ewalletReq, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }


}