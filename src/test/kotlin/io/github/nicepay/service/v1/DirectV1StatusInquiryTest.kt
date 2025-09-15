package io.github.nicepay.service.v1

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.PARTNER_ID
import io.github.nicepay.data.model.DirectV1StatusInquiry
import io.github.nicepay.data.response.v1.NICEPayResponseV1
import io.github.nicepay.service.v1.impl.DirectV1StatusInquiryServiceImpl
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class DirectV1StatusInquiryTest {

    companion object {

        private val TIMESTAMP: String = TestingConstants.V2_TIMESTAMP

        private val DEFAULT_AMOUNT = "10000"
        private val IS_CLOUD_SERVER = false;
        private var I_MID = PARTNER_ID;
        private val IS_PRODUCTION = false;

        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="

        val inquiryService: DirectV1StatusInquiryService = DirectV1StatusInquiryServiceImpl()
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
    fun `Should be able to Status Inquiry Direct V1 Card`() {

        val config = generateConfig()

        val referenceNo  = "ordNo20250820150853"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST01202508201518530657")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }

    @Test
    fun `Should be able to Status Inquiry Direct V1 Virtual Account`() {

        val config = generateConfig()

        val referenceNo  = "ordNo20250903100947"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST02202509031058456349")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }


    @Test
    fun `Should be able to Status Inquiry Direct V1 Convenience Store`() {

        val config = generateConfig()

        val referenceNo  = "ordNo20250821140835"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST03202508211457504916")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }

    @Test
    fun `Should be able to Status Inquiry Direct V1 Direct Debit`() {

        val config = generateConfig()

        val referenceNo  = "ordNo20250124130125"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST00202501241336505519")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }

    @Test
    fun `Should be able to Status Inquiry Direct V1 Ewallet`() {

        val config = generateConfig()

        val referenceNo  = "IONPAYTEST2024102212152236"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST00202410221215239320")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }

    @Test
    fun `Should be able to Status Inquiry Direct V1 Qris`() {

        val config = generateConfig()

        val referenceNo  = "ORDER20221012080919"
        val qrisRequest = DirectV1StatusInquiry.Builder()
            .iMid(config.partnerId)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(referenceNo)
            .tXid("IONPAYTEST00202506101402065016")
            .merchantToken(config.partnerId,
                referenceNo,
                DEFAULT_AMOUNT,
                DEFAULT_MERCHANT_KEY
            )
            .build()

        val response: NICEPayResponseV1? = inquiryService.statusInquiry(qrisRequest, config)

        assertNotNull(response)

        if (response != null) {
            assertEquals("0000", response.resultCd)
        }
    }


}