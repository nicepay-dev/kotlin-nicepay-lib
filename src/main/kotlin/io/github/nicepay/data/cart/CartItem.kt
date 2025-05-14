package io.github.nicepay.data.cart

class CartItem(
    val goods_id : String?,
    val goods_detail : String?,
    val img_url : String?,
    val goods_name : String?,
    val goods_amt : String?,
    val goods_type : String?,
    val goods_url : String?,
    val goods_quantity : String?,
    val goods_sellers_id : String?,
    val goods_sellers_name : String?,
) {

    class Builder {
        private var goodsId: String? = null
        private var goodsDetail: String? = null
        private var imgUrl: String? = null
        private var goodsName: String? = null
        private var goodsAmt: String? = null
        private var goodsType: String? = null
        private var goodsUrl: String? = null
        private var goodsQuantity: String? = null
        private var goodsSellersId: String? = null
        private var goodsSellersName: String? = null

        fun goodsId(goodsId: String?) = apply { this.goodsId = goodsId }
        fun goodsDetail(goodsDetail: String?) = apply { this.goodsDetail = goodsDetail }
        fun imgUrl(imgUrl: String?) = apply { this.imgUrl = imgUrl }
        fun goodsName(goodsName: String?) = apply { this.goodsName = goodsName }
        fun goodsAmt(goodsAmt: String?) = apply { this.goodsAmt = goodsAmt }
        fun goodsType(goodsType: String?) = apply { this.goodsType = goodsType }
        fun goodsUrl(goodsUrl: String?) = apply { this.goodsUrl = goodsUrl }
        fun goodsQuantity(goodsQuantity: String?) = apply { this.goodsQuantity = goodsQuantity }
        fun goodsSellersId(goodsSellersId: String?) = apply { this.goodsSellersId = goodsSellersId }
        fun goodsSellersName(goodsSellersName: String?) = apply { this.goodsSellersName = goodsSellersName }

        fun build(): CartItem {
            return CartItem(
                goodsId,
                goodsDetail,
                imgUrl,
                goodsName,
                goodsAmt,
                goodsType,
                goodsUrl,
                goodsQuantity,
                goodsSellersId,
                goodsSellersName
            )
        }
    }

}