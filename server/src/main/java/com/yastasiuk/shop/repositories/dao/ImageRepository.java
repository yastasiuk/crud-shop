package com.yastasiuk.shop.repositories.dao;

import org.springframework.data.repository.CrudRepository;

import com.yastasiuk.shop.model.Image;

public interface ImageRepository extends CrudRepository<Image, String> {
    public Image save(Image image);
    public Image getImageById(String imageId);
}
