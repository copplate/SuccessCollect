package com.shangeyun.recytantan.adapter

import com.shangeyun.recytantan.R
import com.shangeyun.recytantan.bean.CardStackBean

/*
class CardStackAdapter<T>(data: MutableList<CardStackBean<T>>) :
    BaseQuickAdapter<CardStackBean<T>, BaseViewHolder>(R.layout.item_card_stack, data) {
    override fun convert(helper: BaseViewHolder, item: CardStackBean<T>) {

        helper.itemView.imageView.loadUrl(item.imageUrl)

        helper
            .setText(R.id.tvTitle, item.title)
            .setText(R.id.tvContent, item.content)

        helper.itemView.click {
            "已收藏:${item.content}" toast mContext
        }
    }
}*/
