package io.github.nicepay.service.v2

import io.github.nicepay.data.response.v2.NICEPayResponseV2

interface DirectV2Service<T> : CommonDirectV2Service, V2Service<T, NICEPayResponseV2> {
}