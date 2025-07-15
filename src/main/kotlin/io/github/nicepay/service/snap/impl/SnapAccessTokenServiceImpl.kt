package io.github.nicepay.service.snap.impl


import com.google.gson.GsonBuilder
import io.github.nicepay.api.snap.AccessTokenApi
import io.github.nicepay.data.model.AccessToken
import io.github.nicepay.data.response.snap.NICEPayResponse
import io.github.nicepay.service.snap.SnapAccessTokenService
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import retrofit2.Call


class SnapAccessTokenServiceImpl : SnapAccessTokenService {

    private val print = LoggerPrint()
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun getAccessToken(request: AccessToken, config: NICEPay): NICEPayResponse? {
        val service = ApiUtils.createService(
            serviceClass = AccessTokenApi::class.java,
            grantType = request.getGrantType(),
            accessToken = null,
            data = null,
            config = config
        )

        val call: Call<NICEPayResponse> = service.requestAccessToken(request)
        return try {
            val response = call.execute()
            val successBody = response.body()
            val errorBodyString = response.errorBody()?.string()

            val result = successBody ?: gson.fromJson(errorBodyString, NICEPayResponse::class.java)
            print.logInfoResponse("Response getToken : ${gson.toJson(result)}")
            result
        } catch (e: Exception) {
            print.logError("Error while getting token: ${e.message}")
            null
        }
    }
}