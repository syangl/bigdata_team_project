package com.nd.sl.producer.bean;

import com.nd.sl.common.bean.Data;

import com.nd.sl.common.bean.Data;


    public class goods extends Data {
        private String goodsid;//商品id
        private String goodsname;//商品名字
        private String price;//商品价格

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setValue(Object val){
            context=(String)val;
            String[] split=context.split("\t");
            setGoodsid(split[0]);
            setGoodsname(split[1]);
            setPrice(split[2]);
        }

        @Override
        public String toString() {
            return goodsid + "\t" + goodsname+"\t"+ price;
        }
    }


