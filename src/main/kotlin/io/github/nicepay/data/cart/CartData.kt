package io.github.nicepay.data.cart

class CartData(
    val count : String?,
    val item : List<CartItem>?
) {

    class Builder {
        private var count: String? = null
        private var item: List<CartItem>? = ArrayList()

        fun count(count: String?) = apply { this.count = count }
        fun item(item: List<CartItem>?) = apply { this.item = item }

        fun build(): CartData {
            return CartData(
                count,
                item
            )
        }
    }

}