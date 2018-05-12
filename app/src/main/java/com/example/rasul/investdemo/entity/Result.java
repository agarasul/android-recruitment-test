
package com.example.rasul.investdemo.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("0")
    @Expose
    private String currencyState;
    @SerializedName("1")
    @Expose
    private String currencyName;
    @SerializedName("2")
    @Expose
    private String buyPrice;
    @SerializedName("3")
    @Expose
    private String sellPrice;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("6")
    @Expose
    private Integer spread;
    @SerializedName("7")
    @Expose
    private String date;

    public String getCurrencyState() {
        return currencyState;
    }

    public void setCurrencyState(String currencyState) {
        this.currencyState = currencyState;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String get_4() {
        return _4;
    }

    public void set_4(String _4) {
        this._4 = _4;
    }

    public String get_5() {
        return _5;
    }

    public void set_5(String _5) {
        this._5 = _5;
    }

    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
