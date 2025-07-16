package io.github.nicepay.service.snap.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.TestingConstants.Companion.CLIENT_SECRET
import io.github.nicepay.data.TestingConstants.Companion.I_MID_NORMALTEST
import io.github.nicepay.data.TestingConstants.Companion.NORMALTEST_CLOUD_PRIVATE_KEY
import io.github.nicepay.data.TestingConstants.Companion.RUN_TEST
import io.github.nicepay.data.TestingConstants.Companion.TIMESTAMP
import io.github.nicepay.data.TestingConstants.Companion.generateExternalId
import io.github.nicepay.data.model.*
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.service.snap.SnapVirtualAccountService
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.IOException

class SnapVirtualAccountTest {

    companion object {

        private val timeStamp: String = TestingConstants.V2_TIMESTAMP
        private lateinit var registeredData : NICEPayResponse

        private val DEFAULT_AMOUNT = "10000.00"
        private val DEFAULT_REFERENCE_NO = "kotlinVA"+ timeStamp
        private val IS_CLOUD_SERVER = false;
        
    }

    @Throws(IOException::class)
    fun getToken(config:NICEPay): Any? {
        val service: SnapAccessTokenService = SnapAccessTokenServiceImpl()

        val request = AccessToken.Builder()
            .grantType("client_credentials")
            .additionalInfo(emptyMap())
            .build()

        return service.getAccessToken(request, config)

    }



    @Test
    fun `Should be able to create Virtual Account Number`() {

        if (RUN_TEST){
            val accessToken = (getToken(generateConfig(IS_CLOUD_SERVER)) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")

            val snapVirtualAccountService : SnapVirtualAccountService = SnapVirtualAccountServiceImpl()

            val virtualAccount = SnapVirtualAccount.Builder()
                .partnerServiceId("1234")
                .customerNo("")
                .virtualAccountNo("")
                .virtualAccountName("TESTVaName")
                .trxId(DEFAULT_REFERENCE_NO)
                .totalAmount(DEFAULT_AMOUNT, "IDR")
                .additionalInfo(
                    mapOf(
                        "bankCd" to "BRIN",
                        "goodsNm" to "TESTGoodsNm",
                        "dbProcessUrl" to "https://merchant.com/test",
                        "vacctValidDt" to "",
                        "vacctValidTm" to "",
                        "msId" to "",
                        "msFee" to "",
                        "msFeeType" to "",
                        "mbFee" to "",
                        "mbFeeType" to ""
                    )
                )
                .build()

            val response: NICEPayResponse? = snapVirtualAccountService.createVA(virtualAccount, accessToken, generateConfig(IS_CLOUD_SERVER))

            assertNotNull(response)

            if (response != null) {
                registeredData = response
                assertNotNull(response.virtualAccountData?.get("virtualAccountNo")  )
                assertEquals("Successful", response.responseMessage)
                assertEquals("2002700", response.responseCode)
            }
        } else {
            println("test skipped")
        }
    }

    @Test
    fun `Should be able to inquiry status virtual account transaction`(){
        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val vaTransaction: NICEPayResponse? = createVirtualAccountData(accessToken)

            val reqInquiryVA : SnapCheckStatus = SnapCheckStatus.Builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo(vaTransaction?.virtualAccountData?.get("virtualAccountNo").toString())
                .inquiryRequestId("kotlinVAInq"+TestingConstants.V2_TIMESTAMP)
                .totalAmount(DEFAULT_AMOUNT, "IDR")
                .trxId(vaTransaction?.virtualAccountData?.get("trxId").toString())
                .tXidVA(
                    ((vaTransaction?.virtualAccountData
                        ?.get("additionalInfo") as? Map<*, *>)
                        ?.get("tXidVA") ?: "").toString()
                )
                .build()

            val snapVirtualAccountService : SnapVirtualAccountService = SnapVirtualAccountServiceImpl()
            val response : NICEPayResponse? = snapVirtualAccountService.checkStatus(reqInquiryVA, accessToken, config)

            assertNotNull(response)

            if (response != null) {
                assertNotNull(response.virtualAccountData?.get("virtualAccountNo")  )
                assertEquals("Successful", response.responseMessage)
                assertEquals("2002600", response.responseCode)
            }
        } else {
            println("test skipped")
        }
    }

    @Test
    fun `Should be able to delete virtual account transaction`(){
        if (RUN_TEST){
            val config : NICEPay = generateConfig(IS_CLOUD_SERVER)

            val accessToken = (getToken(config) as? NICEPayResponse)
                ?.accessToken
                ?: throw IllegalArgumentException("Token is null")


            val vaTransaction: NICEPayResponse? = createVirtualAccountData(accessToken)

            val reqDeleteVA : SnapCancel = SnapCancel.Builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo(vaTransaction?.virtualAccountData?.get("virtualAccountNo").toString())
                .cancelMessage("Test delete VA transaction via Kotlin Library")
                .totalAmount(DEFAULT_AMOUNT, "IDR")
                .trxId(vaTransaction?.virtualAccountData?.get("trxId").toString())
                .tXidVA(
                    ((vaTransaction?.virtualAccountData
                        ?.get("additionalInfo") as? Map<*, *>)
                        ?.get("tXidVA") ?: "").toString()
                )
                .build()

            val snapVirtualAccountService : SnapVirtualAccountService = SnapVirtualAccountServiceImpl()
            val response : NICEPayResponse? = snapVirtualAccountService.deleteVA(reqDeleteVA, accessToken, config)

            assertNotNull(response)

            if (response != null) {
                assertNotNull(response.virtualAccountData?.get("virtualAccountNo")  )
                assertEquals("Successful", response.responseMessage)
                assertEquals("2003100", response.responseCode)
            }
        } else{
            println("Test skipped")
        }
    }

    fun generateConfig(isCloud : Boolean) : NICEPay{
        var config: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.NORMALTEST_CLIENT_SECRET)
            .partnerId(I_MID_NORMALTEST)
            .externalID(generateExternalId())
            .timestamp(TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        val configCloud: NICEPay = NICEPay.Builder()
            .isProduction(false)
            .isCloudServer(true)
            .clientSecret(CLIENT_SECRET)
            .partnerId(I_MID_NORMALTEST)
            .externalID(generateExternalId())
            .timestamp(TIMESTAMP)
            .privateKey(NORMALTEST_CLOUD_PRIVATE_KEY)
            .build()

        if(isCloud){
            return configCloud
        } else {
            return config
        }
    }



    fun createVirtualAccountData(accessToken:String): NICEPayResponse? {


        val snapVirtualAccountService : SnapVirtualAccountService = SnapVirtualAccountServiceImpl()

        val virtualAccount = SnapVirtualAccount.Builder()
            .partnerServiceId("")
            .customerNo("")
            .virtualAccountNo("")
            .virtualAccountName("TESTVaName")
            .trxId(DEFAULT_REFERENCE_NO)
            .totalAmount(DEFAULT_AMOUNT, "IDR")
            .additionalInfo(
                mapOf(
                    "bankCd" to "BRIN",
                    "goodsNm" to "TESTGoodsNm",
                    "dbProcessUrl" to "https://merchant.com/test",
                    "vacctValidDt" to "",
                    "vacctValidTm" to "",
                    "msId" to "",
                    "msFee" to "",
                    "msFeeType" to "",
                    "mbFee" to "",
                    "mbFeeType" to ""
                )
            )
            .build()

        return snapVirtualAccountService.createVA(virtualAccount, accessToken, generateConfig(IS_CLOUD_SERVER))
    }



}