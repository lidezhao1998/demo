package com.ruoyi.zaihai.enums;

/**
 * @author sunlei
 * @description 阿拉伯转中文
 * @date 2020/10/22/16:23
 */
public enum ArabicToChineseNumerals {
    //数字

    ONE("1", "一"),

    TWO("2", "二"),

    THREE("3", "三"),

    FOUR("4", "四"),

    FIVE("5", "五"),

    SIX("6", "六"),

    SEVEN("7", "七"),

    EIGHT("8", "八"),

    NINE("9", "九");
    private final String status;

    private final String number;

    ArabicToChineseNumerals(String status, String number) {
        this.status = status;
        this.number = number;
    }


    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }

    public static String getNumber(String status) {
        for (ArabicToChineseNumerals n : ArabicToChineseNumerals.values()) {
            if (n.getStatus().equals(status)) {
                return n.getNumber();
            }
        }
        return "";
    }
}