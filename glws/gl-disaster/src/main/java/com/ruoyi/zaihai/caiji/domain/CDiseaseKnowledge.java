package com.ruoyi.zaihai.caiji.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 草原病害草知识库信息对象 c_disease_knowledge
 *
 * @author ruoyi
 * @date 2020-04-29
 */
public class CDiseaseKnowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 中文名 */
    @Excel(name = "中文名")
    private String middleName;

    /** 病原拉丁名 */
    @Excel(name = "病原拉丁名")
    private String latinName;

    /** 病原中名 */
    @Excel(name = "病原中名")
    private String byName;

    /** 病原形态 */
    @Excel(name = "病原形态")
    private String byForm;

    /** 亚门 */
    @Excel(name = "亚门")
    private String subgate;

    /** 属 */
    @Excel(name = "属")
    private String genus;

    /** 寄生 */
    @Excel(name = "寄生")
    private String host;

    /** 分布 */
    @Excel(name = "分布")
    private String distribution;

    /** 危害部位 */
    @Excel(name = "危害部位")
    private String damageLocation;

    /** 危害症状 */
    @Excel(name = "危害症状")
    private String whSymptom;

    /** 症状 */
    @Excel(name = "症状")
    private String symptom;

    /** 病症 */
    @Excel(name = "病症")
    private String disease;

    /** 发生规律 */
    @Excel(name = "发生规律")
    private String law;

    /** 防治方法 */
    @Excel(name = "防治方法")
    private String method;

    /** 病原形态图 */
    @Excel(name = "病原形态图")
    private String byMorphology;

    /** 症状图 */
    @Excel(name = "症状图")
    private String symptomMap;

    /** 状态 */
    @Excel(name = "状态")
    private String status;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getByName() {
        return byName;
    }

    public void setByName(String byName) {
        this.byName = byName;
    }

    public String getByForm() {
        return byForm;
    }

    public void setByForm(String byForm) {
        this.byForm = byForm;
    }

    public String getSubgate() {
        return subgate;
    }

    public void setSubgate(String subgate) {
        this.subgate = subgate;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDamageLocation() {
        return damageLocation;
    }

    public void setDamageLocation(String damageLocation) {
        this.damageLocation = damageLocation;
    }

    public String getWhSymptom() {
        return whSymptom;
    }

    public void setWhSymptom(String whSymptom) {
        this.whSymptom = whSymptom;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getByMorphology() {
        return byMorphology;
    }

    public void setByMorphology(String byMorphology) {
        this.byMorphology = byMorphology;
    }

    public String getSymptomMap() {
        return symptomMap;
    }

    public void setSymptomMap(String symptomMap) {
        this.symptomMap = symptomMap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "CDiseaseKnowledge{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", middleName='" + middleName + '\'' +
                ", latinName='" + latinName + '\'' +
                ", byName='" + byName + '\'' +
                ", byForm='" + byForm + '\'' +
                ", subgate='" + subgate + '\'' +
                ", genus='" + genus + '\'' +
                ", host='" + host + '\'' +
                ", distribution='" + distribution + '\'' +
                ", damageLocation='" + damageLocation + '\'' +
                ", whSymptom='" + whSymptom + '\'' +
                ", symptom='" + symptom + '\'' +
                ", disease='" + disease + '\'' +
                ", law='" + law + '\'' +
                ", method='" + method + '\'' +
                ", byMorphology='" + byMorphology + '\'' +
                ", symptomMap='" + symptomMap + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
