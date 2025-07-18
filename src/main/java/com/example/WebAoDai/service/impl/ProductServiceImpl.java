package com.example.WebAoDai.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.WebAoDai.service.CloudinaryService;
import com.example.WebAoDai.utils.PixelcutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.WebAoDai.entity.Product;
import com.example.WebAoDai.repository.ProductRepository;
import com.example.WebAoDai.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CloudinaryService cloudinaryService;

    private final PixelcutUtil pixelcutUtil;

    public ProductServiceImpl(ProductRepository productRepository, CloudinaryService cloudinaryService, PixelcutUtil pixelcutUtil) {
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
        this.pixelcutUtil = pixelcutUtil;
    }

    @Override
    public List<Product> getAllProduct() {
        // TODO Auto-generated method stub
        return productRepository.findAllByIsActive(1);
    }

    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(int id) {
        // TODO Auto-generated method stub
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        // TODO Auto-generated method stub
        Product product = productRepository.findById(id).get();
        product.setIsActive(0);
        productRepository.save(product);
    }



    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @Override

    public String tryOnClothes(Integer productId, MultipartFile selfie) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        String selfieUrl = cloudinaryService.uploadFile(selfie);
        return pixelcutUtil.tryOnClothes(selfieUrl, productOpt.get().getProductImage().get(0).getUrl_Image());
    }

    @Override
    public List<Product> findByProduct_NameContaining(String name) {
        // TODO Auto-generated method stub
        return productRepository.findByProduct_NameContaining(name);
    }

    @Override
    public List<Product> findTop12ProductBestSellers() {
        return productRepository.findTop12ProductBestSellers();
    }

    @Override
    public List<Product> findTop12ProductNewArrivals() {
        // TODO Auto-generated method stub
        return productRepository.findTop12ProductNewArrivals();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByIsActive(1, pageable);
    }

    @Override
    public Page<Product> findByProduct_NameAndCategory_idContaining(String name, int category_id, Pageable pageable) {
        return productRepository.findByProduct_NameAndCategory_idContaining(name, category_id, pageable);
    }

    @Override
    public Page<Product> findByProduct_NameContaining(String name, Pageable pageable) {
        return productRepository.findByProduct_NameContaining(name, pageable);
    }

    @Override
    public List<Product> findTop4ProductByCategory_id(int id) {
        return productRepository.findTop4ProductByCategory_id(id);
    }

    @Override
    public List<Product> getProductsByGender(String gender) {
        return productRepository.findByGender(gender);
    }


}
