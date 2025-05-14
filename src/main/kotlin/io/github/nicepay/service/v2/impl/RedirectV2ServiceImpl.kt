package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.RegistrationRedirectV2Api
import io.github.nicepay.data.model.RedirectV2
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.ApiHttpService
import io.github.nicepay.service.v2.RedirectV2Service
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay

class RedirectV2ServiceImpl : RedirectV2Service {

    override fun registration(data: RedirectV2, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val apiHttpService : ApiHttpService<RedirectV2, RegistrationRedirectV2Api> = ApiHttpServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(RegistrationRedirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}