package com.nd.sl.producer.bean;

public class record {
    private String goodsid;//商品id
    private String goodsname;//商品名称
    private String userid;//用户id
    private String goodsprice;//单价
    private String buynum;//数目
    private String buydate;//日期

    public record(String goodsid, String goodsname, String userid, String goodsprice, String buynum, String buydate) {
        this.goodsid = goodsid;
        this.goodsname = goodsname;
        this.userid = userid;
        this.goodsprice = goodsprice;
        this.buynum = buynum;
        this.buydate = buydate;
    }

    @Override
    public String toString() {
        return goodsid + "\t" + goodsname + "\t" + userid + "\t" + goodsprice+"\t"+ buynum+"\t"+buydate;
    }

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(String goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getBuynum() {
        return buynum;
    }

    public void setBuynum(String buynum) {
        this.buynum = buynum;
    }

    public String getBuydate() {
        return buydate;
    }

    public void setBuydate(String buydate) {
        this.buydate = buydate;
    }
}
