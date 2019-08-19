package com.yastasiuk.shop.services;

import com.yastasiuk.shop.model.Image;
import com.yastasiuk.shop.repositories.dao.ImagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImagesDao imagesDao;

    @Override
    public Image saveImageFile(MultipartFile file) {
        try {
            byte[] byteObjects = new byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            Image image = new Image(file.getName(), file.getContentType(), byteObjects);

            return imagesDao.saveImage(image);
        } catch (IOException e) {
            //todo handle better
//            log.error("Error occurred", e);

            e.printStackTrace();
            return null;
        }
    }
}
