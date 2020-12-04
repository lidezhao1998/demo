package com.ruoyi.web.test;

/**
 * @Describe
 * @Author LiRenDong
 * @DATE 2020/11/18
 */
public class person {
    private eact eact;

    public person() {
    }

    public person(com.ruoyi.web.test.eact eact) {
        this.eact = eact;
    }
    public void eactIng(){
        System.out.println(eact.eactIng());
    }

    public static void main(String[] args) {
        person person = new person(new meat());
        person.eactIng();
    }
}
