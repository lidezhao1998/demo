package com.ruoyi.zaihai.caiji.domain;


public class RegionCode  {
    private static final long serialVersionUID = 1L;
    private Long dictCode;
    private String dictLabel;

    public Long getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(Long dictSort)
    {
        this.dictCode = dictSort;
    }

    public String getDictLabel()
    {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel)
    {
        this.dictLabel = dictLabel;
    }
}
