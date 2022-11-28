package com.birichani.code.controller;


import com.birichani.code.model.Product;
import com.birichani.code.payload.ProductDTO;
import com.birichani.code.payload.ProductResponse;

import com.birichani.code.repository.ProductRepository;
import com.birichani.code.service.ProductService;
import com.birichani.code.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    private ProductService productService ;
    @Autowired
    private ProductRepository productRepository;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create product item rest api
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO ){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // get all items rest api
    @GetMapping("/products")
    public ProductResponse getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return productService.getAllProduct(pageNo, pageSize, sortBy, sortDir);
    }
    //get items returned last month from vendors

    @GetMapping(value = "/vendor/product/lastMonth")
    List<Product>findByItemsCreatedBetweenLastMonth() throws ParseException{
        Date monthStart=new SimpleDateFormat("YYYY-MM-DD").parse("2022-01-10");
        Date endMonth=new SimpleDateFormat("YYYY-MM-DD").parse("2022-30-10");
        return productRepository.findByItemsCreatedBetweenLastMonth(monthStart,endMonth);
    }

    // get items received from vendors today
    @GetMapping(value = "/vendor/product/today")
    List<Product>findByItemsReceivedToday() throws ParseException{
        Date dayStart=new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("");
        Date dayEnd=new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("");
        return productRepository.findByItemsReceivedToday(dayStart,dayEnd
        );
    }
    // get items received from vendors last week
    @GetMapping(value = "/vendor/product/lastWeek")
    List<Product>findByItemsReceivedLastWeek() throws ParseException {
        Date weekStart = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("");
        Date weekEnd = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("");
        return productRepository.findByItemsReceivedLastWeek(weekStart, weekEnd
        );

    }


    // get product by id
    @GetMapping(value = "/product{name}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // update product by id rest api
    @PutMapping("/editProduct/{id}")
    public ResponseEntity<ProductDTO> updateChild(@Valid @RequestBody ProductDTO productDTO, @PathVariable(name = "id") long id){

        ProductDTO productResponse = productService.updateProduct(productDTO, id);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    // delete product rest api
    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id){

       productService.deleteProductById(id);

        return new ResponseEntity<>("product entity deleted successfully.", HttpStatus.OK);
    }
}
