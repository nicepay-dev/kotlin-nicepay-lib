package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.RegistrationDirectV2Api
import io.github.nicepay.data.model.DirectV2Cvs
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.ApiHttpService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay

class DirectV2CvsServiceImpl : CommonDirectV2ServiceImpl(), DirectV2Service<DirectV2Cvs> {

    override fun registration(data: DirectV2Cvs, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val apiHttpService : ApiHttpService<DirectV2Cvs, RegistrationDirectV2Api> = ApiHttpServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(RegistrationDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}