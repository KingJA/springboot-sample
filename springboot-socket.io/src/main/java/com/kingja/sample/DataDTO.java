package com.kingja.sample;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 18:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataDTO {
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public DataDTO() {
    }

    public DataDTO(int type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
