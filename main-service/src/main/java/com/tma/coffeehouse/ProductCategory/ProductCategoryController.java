package com.tma.coffeehouse.ProductCategory;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    @GetMapping("/prod-category")
    public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
        return new ResponseEntity<>(productCategoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/prod-category/image/{id}", produces = IMAGE_PNG_VALUE )
    public byte[] getProductCategoryImage(@PathVariable Long id){
        return productCategoryService.getProductCategoryImage(id);
    }
    @PostMapping("/admin/prod-category")
    public ResponseEntity<ProductCategory> insertProductCategory(@RequestParam("image") MultipartFile multipartFile,@RequestParam("name") String name) throws IOException {


        return new ResponseEntity<>(productCategoryService.insert(name, multipartFile), HttpStatus.OK);
    }

    @PutMapping({"/admin/prod-category/{id}"})
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable long id, @RequestParam("image") MultipartFile multipartFile,@RequestParam("name") String name){
        return new ResponseEntity<>(productCategoryService.update(id, name, multipartFile), HttpStatus.OK);
    }

    @DeleteMapping({"/admin/prod-category/{id}"})
    public  ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable long id){
        return new ResponseEntity<>(productCategoryService.delete(id), HttpStatus.OK);
    }
}
