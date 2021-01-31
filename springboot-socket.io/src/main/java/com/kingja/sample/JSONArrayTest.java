package com.kingja.sample;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 19:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class JSONArrayTest {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(1);
        jsonArray.put("好的");
        jsonArray.put(JSONUtil.objectToJson(new DataDTO(1, "消息")));
        System.out.println(jsonArray);
    }
}
