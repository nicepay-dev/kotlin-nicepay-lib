package io.github.nicepay.service.v1

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.PARTNER_ID
import io.github.nicepay.data.model.DirectV1Cancel
import io.github.nicepay.data.model.DirectV1VirtualAccount
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.DirectV1CancelService
import io.github.nicepay.service.v1.DirectV1VirtualAccountTest.Companion.vaService
import io.github.nicepay.service.v1.impl.DirectV1CancelServiceImpl
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class DirectV1CancelTest {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private val DEFAULT_AMOUNT = "10000"
        private val IS_CLOUD_SERVER = false;
        private var I_MID = PARTNER_ID;
        private val IS_PRODUCTION = false;
        private val DEFAULT_REFERENCE_NO = "ordNo"+ TIMESTAMP

        private val DEFAULT_MERCHANT_KEY = CLIENT_SECRET

        val cancelService: DirectV1CancelService = DirectV1CancelServiceImpl()
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
    fun `Should be able to Status Inquiry Direct V1 Virtual Account`() {

        val config = generateConfig()

        val vaTrans = generateVA(config)

        val referenceNo  = vaTrans?.referenceNo
        val tXid = vaTrans?.tXid

        val cancelRequest = DirectV1Cancel.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid(tXid)
            .merchantToken(config.partnerId,
                tXid,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .payMethod("02")
            .cancelType("1")
            .cancelMsg("Test Cancel V1 Kotlin")
            .fee("0")
            .vat("0")
            .notaxAmt("0")
            .reqServerIP("127.0.0.1")
            .cancelUserInfo("Nicepay library")
            .cancelRetryCnt("2")
            .worker("library")
            .build()

        val response: NICEPayResponseV1? = cancelService.cancel(cancelRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }

    fun generateVA(config: NICEPay):NICEPayResponseV1? {

        val config = generateConfig()

        val virtualAccount = DirectV1VirtualAccount.Builder()
            .iMid(config.partnerId)
            .payMethod("02")
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
            .merFixAcctId("")
            .vacctValidDt("")
            .vacctValidTm("")
            .bankCd("BMRI")
            .build()

        return vaService.generateVirtualAccount(virtualAccount, config)

    }



}