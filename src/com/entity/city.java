package com.entity;

public class city {
    private Integer id;
    private String name;
    private Integer provinceId;

    public city(Integer id, String name, Integer provinceId) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
    }

    public city() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
