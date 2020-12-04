package com.ruoyi.system.domain.gis;

/**
 * @author sunlei
 * @description 生态属性实体
 * @date 2020/08/11/17:17
 */
public class EologicalAttributes {
    private  int id;
    //目
    private String goal;
    //科
    private String familie;
    //属
    private String subordinate;
    //从
    private String follow;
    //目代码
    private String goalCode;
    //科代码
    private String familieCode;
    //属代码
    private String subordinateCode;
    //从代码
    private String followCode;

    public EologicalAttributes() {
    }

    public EologicalAttributes(int id, String goal, String familie, String subordinate, String follow, String goalCode, String familieCode, String subordinateCode, String followCode) {
        this.id = id;
        this.goal = goal;
        this.familie = familie;
        this.subordinate = subordinate;
        this.follow = follow;
        this.goalCode = goalCode;
        this.familieCode = familieCode;
        this.subordinateCode = subordinateCode;
        this.followCode = followCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getFamilie() {
        return familie;
    }

    public void setFamilie(String familie) {
        this.familie = familie;
    }

    public String getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getGoalCode() {
        return goalCode;
    }

    public void setGoalCode(String goalCode) {
        this.goalCode = goalCode;
    }

    public String getFamilieCode() {
        return familieCode;
    }

    public void setFamilieCode(String familieCode) {
        this.familieCode = familieCode;
    }

    public String getSubordinateCode() {
        return subordinateCode;
    }

    public void setSubordinateCode(String subordinateCode) {
        this.subordinateCode = subordinateCode;
    }

    public String getFollowCode() {
        return followCode;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode;
    }
}
