package io.github.nicepay.utils.code

enum class NICEPayMethod(val code : String) {
    
     PAY_METHOD_ALL("00"),
     PAY_METHOD_CARD("01"),
     PAY_METHOD_VIRTUAL_ACCOUNT("02"),
     PAY_METHOD_CONVINIENCE_STORE("03"),
     PAY_METHOD_EWALLET("05"),
     PAY_METHOD_PAYLOAN("06"),
     PAY_METHOD_PAYOUT("07"),
     PAY_METHOD_QRIS("08")
    
}