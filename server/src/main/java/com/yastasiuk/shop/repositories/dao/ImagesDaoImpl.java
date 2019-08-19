package com.yastasiuk.shop.repositories.dao;

import com.yastasiuk.shop.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagesDaoImpl implements ImagesDao {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
}
