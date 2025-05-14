package io.github.nicepay.service.v2

import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet

interface DirectV2PaymentService<T : DirectV2RequestPaymentEwallet> : V2Service<T, String> {
}