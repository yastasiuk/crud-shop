package com.yastasiuk.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] image;

    protected Image() {}

    public Image(String imageName, String imageType, byte[] image) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public byte[] getImage() {
        return image;
    }
}
