package com.yastasiuk.shop.services;

import com.yastasiuk.shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public Image saveImageFile(MultipartFile file);
}
