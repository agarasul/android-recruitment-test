
package com.example.rasul.investdemo.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocketResponse {


    @SerializedName("message")
    @Expose
    private String message = null;

    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
