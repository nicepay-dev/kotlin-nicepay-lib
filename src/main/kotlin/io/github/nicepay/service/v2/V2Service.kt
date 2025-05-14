package io.github.nicepay.service.v2

import io.github.nicepay.utils.NICEPay

interface V2Service<T, U> {

    fun registration(data: T, config: NICEPay?): U?

}