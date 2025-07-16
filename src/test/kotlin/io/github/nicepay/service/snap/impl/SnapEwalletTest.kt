package io.github.nicepay.service.snap.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.CLOUD_PRIVATE_KEY
import io.github.nicepay.data.TestingConstants.Companion.I_MID_EWALLET
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import io.github.nicepay.data.TestingConstants.Companion.TIMESTAMP
import io.github.nicepay.data.TestingConstants.Companion.generateExternalId
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.model.SnapCancel
import io.github.nicepay.data.model.SnapCheckStatus
import io.github.nicepay.data.model.SnapEwallet
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import java.io.IOException
import kotlin.test.Test

class SnapEwalletTest {

    companion object {

        private val snapEwalletService = SnapEwalletServiceImpl()
        private val print: LoggerPrint = LoggerPrint()

        private val timeStamp: String = TestingConstants.V2_TIMESTAMP
        private val DEFAULT_AMOUNT = "1.00"
        private val DEFAULT_REFERENCE_NO = "kotlinEwallet"+ timeStamp
        private val IS_CLOUD_SERVER = false;
    }

    @Test
    fun `Should be able to generate Ewallet transaction`(){
        if (RUN_TEST){
            val config = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")

            val ewallet = SnapEwallet.Builder()
                .partnerReferenceNo(DEFAULT_REFERENCE_NO)
                .merchantId(config.partnerId!!)
                .subMerchantId("")
                .externalStoreId("")
                .validUpTo("")
                .pointOfInitiation("Mobile App")
                .amount(DEFAULT_AMOUNT, "IDR")
                .additionalInfo(
                    mapOf(
                        "mitraCd" to "DANA",
                        "goodsNm" to "Testing SNAP Ewallet via Kotlin Library",
                        "billingNm" to "Kotlin Library",
                        "billingPhone" to "089665542347",
                        "dbProcessUrl" to "https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d",
                        "callBackUrl" to config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp",
                        "msId" to "",
                        "cartData" to "{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0.00\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"1.00\",\"goods_quantity\":\"1\"}]}"
                    )
                )
                .urlParam(
                    arrayOf(
                        arrayOf("https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d", "PAY_NOTIFY", "Y"),
                        arrayOf(config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp", "PAY_RETURN", "Y")
                    )
                )
                .build()

            val response = snapEwalletService.paymentHostToHost(ewallet, accessToken, config)

            assertNotNull(response)

            if (response != null) {
                assertNotNull(response.webRedirectUrl)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2005400", response.responseCode)
            }
        } else {
            println("Test skipped")
        }
    }


    @Test
    fun `Should be able to inquiry status Ewallet transaction`(){

        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val ewalletTransaction: NICEPayResponse? = createEwalletTransaction(accessToken)

            Thread.sleep(10000)

            val checkStatusEwallet : SnapCheckStatus = SnapCheckStatus.Builder()
                .merchantId(config.partnerId!!)
                .subMerchantId("")
                .originalPartnerReferenceNo(ewalletTransaction!!.partnerReferenceNo!!)
                .originalReferenceNo(ewalletTransaction.referenceNo!!)
                .serviceCode("54")
                .transactionDate(TestingConstants.TIMESTAMP)
                .externalStoreId("")
                .amount(DEFAULT_AMOUNT, "IDR")
                .build()


            val response : NICEPayResponse? = snapEwalletService.checkStatus(checkStatusEwallet, accessToken, config)

            assertNotNull(response)
            if (response != null) {
                assertNotNull(response.latestTransactionStatus)
                assertNotNull(response.transactionStatusDesc)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2005500", response.responseCode)
            }
        } else {
            println("Test skipped")
        }

    }

    @Test
    fun `Should be able to refund Ewallet transaction`(){
        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val ewalletTransaction: NICEPayResponse? = createEwalletTransaction(accessToken)

            print.logInfo("Pay the transaction on : " + ewalletTransaction!!.webRedirectUrl)
            Thread.sleep(20000)

            val refundEwallet : SnapCancel = SnapCancel.Builder()
                .merchantId(config.partnerId!!)
                .subMerchantId("")
                .originalPartnerReferenceNo(ewalletTransaction.partnerReferenceNo!!)
                .originalReferenceNo(ewalletTransaction.referenceNo!!)
                .partnerRefundNo("refund"+TestingConstants.V2_TIMESTAMP)
                .externalStoreId("")
                .refundAmount(DEFAULT_AMOUNT, "IDR")
                .refundType("1")
                .reason("Refund Ewallet trans via Kotlin Library")
                .build()

            val response : NICEPayResponse? = snapEwalletService.refund(refundEwallet, accessToken, config)

            assertNotNull(response)
            if (response != null) {
                assertNotNull(response.refundNo)
                assertNotNull(response.refundTime)
                assertEquals("Successful", response.responseMessage)
                assertEquals("2005800", response.responseCode)
            }
        } else {
            println("Test skipped")
        }
    }

    fun createEwalletTransaction(accessToken:String) : NICEPayResponse?{

        val config = generateConfig(IS_CLOUD_SERVER)

        val ewallet = SnapEwallet.Builder()
            .partnerReferenceNo(DEFAULT_REFERENCE_NO)
            .merchantId(config.partnerId!!)
            .subMerchantId("")
            .externalStoreId("")
            .validUpTo("")
            .pointOfInitiation("Mobile App")
            .amount(DEFAULT_AMOUNT, "IDR")
            .additionalInfo(
                mapOf(
                    "mitraCd" to "DANA",
                    "goodsNm" to "Testing SNAP Ewallet via Kotlin Library",
                    "billingNm" to "Kotlin Library",
                    "billingPhone" to "089665542347",
                    "dbProcessUrl" to "https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d",
                    "callBackUrl" to config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp",
                    "msId" to "",
                    "cartData" to "{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0.00\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"1.00\",\"goods_quantity\":\"1\"}]}"
                )
            )
            .urlParam(
                arrayOf(
                    arrayOf("https://webhook.site/9a39d64e-b670-463f-b7c6-bfd555d1154d", "PAY_NOTIFY", "Y"),
                    arrayOf(config.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp", "PAY_RETURN", "Y")
                )
            )
            .build()

        return snapEwalletService.paymentHostToHost(ewallet, accessToken, config)
    }


    fun generateConfig(isCloud : Boolean) : NICEPay{
        var config: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.EWALLET_CLIENT_SECRET)
            .partnerId(TestingConstants.I_MID_EWALLET)
            .externalID(TestingConstants.generateExternalId())
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(CLIENT_SECRET)
            .partnerId(I_MID_EWALLET)
            .externalID(generateExternalId())
            .timestamp(TIMESTAMP)
            .privateKey(CLOUD_PRIVATE_KEY)
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