package com.example.WebAoDai.controller;

import com.example.WebAoDai.entity.Product;
import com.example.WebAoDai.entity.ProductDto;
import com.example.WebAoDai.entity.ProductImage;
import com.example.WebAoDai.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public ResponseEntity<List<ProductDto>> getProducts() {
//        try {
//            List<Product> products = productService.getProducts();
//
//            List<ProductDto> productDtos = products.stream()
//                    .map(this::convertToDto)
//                    .toList();
//
//            return ResponseEntity.ok(productDtos);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }


    @PostMapping("/try-on")
    public ResponseEntity<String> tryOnClothes(@RequestParam("productId") Integer productId,
                                               @RequestParam("selfie") MultipartFile selfie) {
        try {
            String resultUrl = productService.tryOnClothes(productId, selfie);
            return ResponseEntity.ok(resultUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing the try-on request");
        }
    }
    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProduct_Name(product.getProduct_Name());
        dto.setDescription(product.getDescription());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setIsActive(product.getIsActive());
        dto.setCreated_At(product.getCreated_At());
        dto.setSold(product.getSold());
        dto.setProductImage(product.getProductImage());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        dto.setImageUrls(product.getProductImage().stream()
                .map(ProductImage::getUrl_Image)
                .collect(Collectors.toList()));

        return dto;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(value = "gender", required = false) String gender) {
        try {
            List<Product> products;

            if (gender != null && !gender.isBlank()) {
                products = productService.getProductsByGender(gender);
            } else {
                products = productService.getProducts();
            }

            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToDto)
                    .toList();

            return ResponseEntity.ok(productDtos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


}
