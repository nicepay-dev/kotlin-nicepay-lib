package io.github.nicepay.service.v2

import io.github.nicepay.data.model.DirectV2ApproveOrRejectPayout
import io.github.nicepay.data.model.DirectV2CancelPayout
import io.github.nicepay.data.model.DirectV2InquiryPayout
import io.github.nicepay.data.model.DirectV2Payout
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.PayoutAction

interface DirectV2PayoutService {

    fun requestPayout(data: DirectV2Payout, config: NICEPay?): NICEPayResponseV2?

    fun inquiryPayout(data: DirectV2InquiryPayout, config: NICEPay?): NICEPayResponseV2?

    fun cancel(data: DirectV2CancelPayout, config: NICEPay?): NICEPayResponseV2?

    fun approveOrRejectPayout(data : DirectV2ApproveOrRejectPayout, config: NICEPay?, action: PayoutAction): NICEPayResponseV2?

}