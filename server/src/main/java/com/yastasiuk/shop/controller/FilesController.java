package com.yastasiuk.shop.controller;

import com.yastasiuk.shop.model.Image;
import com.yastasiuk.shop.repositories.dao.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/images")
@Controller
public class FilesController {
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable String fileId) {
        Image image = imageRepository.getImageById(fileId);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(image.getImage(), HttpStatus.OK);

        return responseEntity;
    };
}
