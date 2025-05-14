package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.*
import io.github.nicepay.data.model.DirectV2ApproveOrRejectPayout
import io.github.nicepay.data.model.DirectV2CancelPayout
import io.github.nicepay.data.model.DirectV2InquiryPayout
import io.github.nicepay.data.model.DirectV2Payout
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PayoutService
import io.github.nicepay.service.v2.ApiHttpService
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.PayoutAction

class DirectV2PayoutServiceImpl : DirectV2PayoutService {

    override fun requestPayout(data: DirectV2Payout, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val apiHttpService : ApiHttpService<DirectV2Payout, RequestPayoutDirectV2Api> = ApiHttpServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(RequestPayoutDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

    override fun inquiryPayout(data: DirectV2InquiryPayout, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val apiHttpService : ApiHttpService<DirectV2InquiryPayout, InquiryPayoutDirectV2Api> = ApiHttpServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(InquiryPayoutDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

    override fun cancel(data: DirectV2CancelPayout, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val apiHttpService : ApiHttpService<DirectV2CancelPayout, CancelPayoutDirectV2Api> = ApiHttpServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(CancelPayoutDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

    override fun approveOrRejectPayout(
        data: DirectV2ApproveOrRejectPayout,
        config: NICEPay?,
        action: PayoutAction
    ): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null

        try {

            if (action == PayoutAction.APPROVE) {
                val apiHttpService : ApiHttpService<DirectV2ApproveOrRejectPayout, ApprovePayoutDirectV2Api> = ApiHttpServiceImpl()
                nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(ApprovePayoutDirectV2Api::class.java, config!!), config)
            } else {
                val apiHttpService : ApiHttpService<DirectV2ApproveOrRejectPayout, RejectPayoutDirectV2Api> = ApiHttpServiceImpl()
                nicePayResponse = apiHttpService.generate(data, ApiUtils.createServiceV2(RejectPayoutDirectV2Api::class.java, config!!), config)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}