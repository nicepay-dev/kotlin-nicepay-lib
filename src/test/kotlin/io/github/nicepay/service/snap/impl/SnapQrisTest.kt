package io.github.nicepay.service.snap.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.service.snap.SnapQrisService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.io.IOException
import kotlin.test.Test

class SnapQrisTest {

    companion object {

        private val snapQrisService : SnapQrisService = SnapQrisServiceImpl()
        private val print: LoggerPrint = LoggerPrint()

        private val timeStamp: String = TestingConstants.V2_TIMESTAMP
        private val DEFAULT_AMOUNT = "1000.00"
        private val DEFAULT_REFERENCE_NO = "kotlinQris"+ timeStamp
        private val IS_CLOUD_SERVER = false;
        
    }

    @Test
    fun `Should be able to generate QRIS`(){
        if (RUN_TEST){
            val config = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")

            val qris = SnapQris.Builder()
                .merchantId(config.partnerId!!)
                .storeId("NICEPAY")
                .amount(DEFAULT_AMOUNT, "IDR")
                .partnerReferenceNo(DEFAULT_REFERENCE_NO)
                .validityPeriod("")
                .additionalInfo(mapOf(
                    "mitraCd" to "QSHP",
                    "dbProcessUrl" to "https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d",
                    "callBackUrl" to config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp",
                    "cartData" to "{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\\\",\\\"goods_name\\\":\\\"Nokia 3360\\\",\\\"goods_detail\\\":\\\"Old Nokia 3360\\\",\\\"goods_amt\\\":\\\"1000.00\\\",\\\"goods_quantity\\\":\\\"1\\\"}]}",
                    "goodsNm" to "Qris Kotlin Library",
                    "billingNm" to "Kotlin Library",
                    "billingPhone" to "621234567890",
                    "billingEmail" to "nicepaykotlin@email.com",
                    "billingCity" to "Jakarta Selatan",
                    "billingAddr" to "Jl. Di Jakarta 1",
                    "billingState" to "DKI Jakarta",
                    "billingPostCd" to "11111",
                    "billingCountry" to "Indonesia",
                    "userIP" to "127.0.0.1",
                    "msId" to "",
                    "msFee" to "",
                    "msFeeType" to "",
                    "mbFee" to "",
                    "mbFeeType" to ""
                ))
                .build()

            val response = snapQrisService.qrMpmGenerate(qris, accessToken, config)
//        https://dev.nicepay.co.id/nicepay/api/generateQrisImage/KASIRTEST108202507091019227860

            assertNotNull(response)

            if (response != null) {
                assertNotNull(response.qrContent)
                assertNotNull(response.qrUrl)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2004700", response.responseCode)
            }
        } else {
            println("test skipped")
        }
    }


    @Test
    fun `Should be able to inquiry status QRIS transaction`(){
        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val qrisTrans: NICEPayResponse? = createQrisTransaction(accessToken)

            val checkStatusEwallet : SnapCheckStatus = SnapCheckStatus.Builder()
                .merchantId(config.partnerId!!)
                .originalPartnerReferenceNo(qrisTrans!!.partnerReferenceNo!!)
                .originalReferenceNo(qrisTrans.referenceNo!!)
                .serviceCode("51")
                .externalStoreId("NICEPAY")
                .build()


            val response : NICEPayResponse? = snapQrisService.checkStatus(checkStatusEwallet, accessToken, config)

            assertNotNull(response)
            if (response != null) {
                assertNotNull(response.latestTransactionStatus)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2005100", response.responseCode)
            }
        } else {
            println("test skipped")
        }
    }

    @Test
    fun `Should be able to cancel Qris transaction`(){
        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val qrisTrans: NICEPayResponse? = createQrisTransaction(accessToken)

            print.logInfo("Pay the transaction on : " + config.getNICEPayBaseUrl() + "nicepay/api/generateQrisImage/" + qrisTrans!!.referenceNo)
            Thread.sleep(10000)

            val refundQris : SnapCancel = SnapCancel.Builder()
                .merchantId(config.partnerId!!)
                .originalPartnerReferenceNo(qrisTrans.partnerReferenceNo!!)
                .originalReferenceNo(qrisTrans.referenceNo!!)
                .partnerRefundNo("refundQr"+TestingConstants.V2_TIMESTAMP)
                .externalStoreId("NICEPAY")
                .refundAmount(DEFAULT_AMOUNT, "IDR")
                .reason("Refund Qris trans via Kotlin Library")
                .additionalInfo(
                    mapOf(
                        "cancelType" to "1"
                    )
                )
                .build()

            val response : NICEPayResponse? = snapQrisService.refund(refundQris, accessToken, config)

            assertNotNull(response)
            if (response != null) {
                assertNotNull(response.refundTime)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2007800", response.responseCode)
            }
        } else {
            println("test skipped")
        }
    }

    fun createQrisTransaction(accessToken:String) : NICEPayResponse?{

        val config = generateConfig(IS_CLOUD_SERVER)

        val qris = SnapQris.Builder()
            .merchantId(config.partnerId!!)
            .storeId("NICEPAY")
            .amount(DEFAULT_AMOUNT, "IDR")
            .partnerReferenceNo(DEFAULT_REFERENCE_NO)
            .validityPeriod("")
            .additionalInfo(mapOf(
                "mitraCd" to "QSHP",
                "dbProcessUrl" to "https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d",
                "callBackUrl" to config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp",
                "cartData" to "{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\\\",\\\"goods_name\\\":\\\"Nokia 3360\\\",\\\"goods_detail\\\":\\\"Old Nokia 3360\\\",\\\"goods_amt\\\":\\\"1000.00\\\",\\\"goods_quantity\\\":\\\"1\\\"}]}",
                "goodsNm" to "Qris Kotlin Library",
                "billingNm" to "Kotlin Library",
                "billingPhone" to "621234567890",
                "billingEmail" to "nicepaykotlin@email.com",
                "billingCity" to "Jakarta Selatan",
                "billingAddr" to "Jl. Di Jakarta 1",
                "billingState" to "DKI Jakarta",
                "billingPostCd" to "11111",
                "billingCountry" to "Indonesia",
                "userIP" to "127.0.0.1",
                "msId" to "",
                "msFee" to "",
                "msFeeType" to "",
                "mbFee" to "",
                "mbFeeType" to ""
            ))
            .build()

        return snapQrisService.qrMpmGenerate(qris, accessToken, config)
    }


    fun generateConfig(isCloud : Boolean) : NICEPay{
        var config: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.QRIS_CLIENT_SECRET)
            .partnerId(TestingConstants.I_MID_QRIS)
            .externalID(TestingConstants.generateExternalId())
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(TestingConstants.CLIENT_SECRET)
            .partnerId(TestingConstants.PARTNER_ID)
            .externalID(TestingConstants.generateExternalId())
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.IONPAYTEST_CLOUD_PRIVATE_KEY)
            .build()

        if(isCloud){
            return configCloud
        } else {
            return config
        }
    }

    @Throws(IOException::class)
    fun getToken(config: NICEPay): Any? {
        val service: SnapAccessTokenService = SnapAccessTokenServiceImpl()

        val request = AccessToken.Builder()
            .grantType("client_credentials")
            .additionalInfo(emptyMap())
            .build()

        return service.getAccessToken(request, config)
    }
}