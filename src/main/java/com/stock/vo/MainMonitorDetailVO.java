package com.stock.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MainMonitorDetailVO extends BaseVO {

    private String stockCode;

    private MainMonitorDetailTitleVO title;

    private MainMonitorDetailNoticeVO notice;

    private List<JSONObject> pricechange;

    private List<MainMonitorDetailBehaviourVO> list;

    public static void main(String[] args) {
        String a = "{\n" +
                "\t\"errorcode\": 0,\n" +
                "\t\"msg\": \"\",\n" +
                "\t\"notice\": [],\n" +
                "\t\"pricechange\": [{\n" +
                "\t\t\t\"1\": \"201904190930\",\n" +
                "\t\t\t\"2525646\": -4.4488\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"1\": \"201904190930\",\n" +
                "\t\t\t\"2525646\": -4.4488\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"list\": [{\n" +
                "\t\t\t\"nature\": \"主力主买\",\n" +
                "\t\t\t\"volume\": \"475手\",\n" +
                "\t\t\t\"tradetype\": \"1\",\n" +
                "\t\t\t\"avgprice\": \"20.04\",\n" +
                "\t\t\t\"value\": \"95万\",\n" +
                "\t\t\t\"money\": 951900,\n" +
                "\t\t\t\"ctime\": \"15:00:00\",\n" +
                "\t\t\t\"otime\": \"15:00:00\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"nature\": \"主力被买\",\n" +
                "\t\t\t\"volume\": \"500手\",\n" +
                "\t\t\t\"tradetype\": \"1\",\n" +
                "\t\t\t\"avgprice\": \"20.04\",\n" +
                "\t\t\t\"value\": \"100万\",\n" +
                "\t\t\t\"money\": 1002000,\n" +
                "\t\t\t\"ctime\": \"15:00:00\",\n" +
                "\t\t\t\"otime\": \"15:00:00\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"title\": {\n" +
                "\t\t\"stockcode\": \"000723\",\n" +
                "\t\t\"stockname\": \"美锦能源\",\n" +
                "\t\t\"price\": \"20.04\",\n" +
                "\t\t\"profit\": \"-0.94%\",\n" +
                "\t\t\"mainsell\": \"23.28亿\",\n" +
                "\t\t\"mainbuy\": \"16.84亿\",\n" +
                "\t\t\"istrade\": false\n" +
                "\t},\n" +
                "\t\"selfstock\": [{\n" +
                "\t\t\t\"stockcode\": \"300723\",\n" +
                "\t\t\t\"stockname\": \"一品红\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"600517\",\n" +
                "\t\t\t\"stockname\": \"置信电气\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"002547\",\n" +
                "\t\t\t\"stockname\": \"春兴精工\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"600877\",\n" +
                "\t\t\t\"stockname\": \"中国嘉陵\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"300099\",\n" +
                "\t\t\t\"stockname\": \"精准信息\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"300194\",\n" +
                "\t\t\t\"stockname\": \"福安药业\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"600864\",\n" +
                "\t\t\t\"stockname\": \"哈投股份\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"stockcode\": \"600150\",\n" +
                "\t\t\t\"stockname\": \"中国船舶\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        final MainMonitorDetailVO mainMonitorDetailVO = JSONObject.parseObject(a, MainMonitorDetailVO.class);
        System.out.println(mainMonitorDetailVO);
    }

}
