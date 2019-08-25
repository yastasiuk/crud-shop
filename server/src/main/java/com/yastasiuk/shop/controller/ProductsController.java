package com.yastasiuk.shop.controller;

import com.yastasiuk.shop.model.Image;
import com.yastasiuk.shop.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import com.yastasiuk.shop.repositories.dao.ProductDaoImpl;
import com.yastasiuk.shop.model.Product;
import com.yastasiuk.shop.storage.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/api/products")
@RestController
public class ProductsController {
    @Autowired
    ProductDaoImpl productsDao;

//    @Autowired
//    StorageService storageService;
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Product> productsList() {
        return productsDao.getAllProducts();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Product createProduct(MultipartHttpServletRequest request) {
        MultipartFile imageFile = request.getFile("image");
        List<Image> images = new ArrayList<>();
        if (imageFile != null) {
            images.add(imageService.saveImageFile(imageFile));
        }

        Product product = new Product(
            request.getParameter("name"),
            request.getParameter("series"),
            request.getParameter("manufacturer"),
            request.getParameter("vendorCode"),
            images.stream().map(Image::getId).collect(Collectors.toList())
        );

        return productsDao.createProduct(product);
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("productId") String productId)throws ResponseStatusException {
        Product product = productsDao.getProductById(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product %s not found", productId));
        }
        return product;
    }

    @RequestMapping(value = "{productId}/image/{imageId}", method = RequestMethod.GET)
    public Product getProductImage(@PathVariable("productId") String productId, 
            @PathVariable("imageId") String imageId) throws ResponseStatusException {
        Product product = productsDao.getProductById(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product %s not found", productId));
        }
        return product;
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.PATCH)
    public Product updateProduct(@PathVariable("productId") String productId, MultipartHttpServletRequest request)
            throws ResponseStatusException {

        Product product = productsDao.getProductById(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product %s not found", productId));
        }

        if (request.getParameter("name") != null) {
            product.setName(request.getParameter("name"));
        }

        if (request.getParameter("series") != null) {
            product.setSeries(request.getParameter("series"));
        }

        if (request.getParameter("manufacturer") != null) {
            product.setManufacturer(request.getParameter("manufacturer"));
        }

        if (request.getParameter("vendorCode") != null) {
            product.setVendorCode(request.getParameter("vendorCode"));
        }


        MultipartFile imageFile = request.getFile("image");
        List<Image> images = new ArrayList<>();
        if (imageFile != null) {
            images.add(imageService.saveImageFile(imageFile));
            product.setImageIds(images.stream().map(Image::getId).collect(Collectors.toList()));
        }

        productsDao.updateProduct(product);

        return product;
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("productId") String productId)
            throws ResponseStatusException {

        productsDao.deleteProduct(productId);
    
        return new ResponseEntity(HttpStatus.OK);
    }
}
