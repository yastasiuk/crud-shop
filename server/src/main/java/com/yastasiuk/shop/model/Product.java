package com.yastasiuk.shop.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String series;
    private String manufacturer;
    private String vendorCode;
    @ElementCollection
    private List<String> imageIds = new ArrayList<>();

    protected Product() {}

    public Product(String name, String series, String manufacturer, String vendorCode, List<String> imageIds) {
        this.name = name;
        this.series = series;
        this.manufacturer = manufacturer;
        this.vendorCode = vendorCode;
        this.imageIds = imageIds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }
}
