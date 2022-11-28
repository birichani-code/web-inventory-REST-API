package com.birichani.code.service;

import com.birichani.code.payload.ProductDTO;
import com.birichani.code.payload.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductResponse getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDTO findByItemsCreatedBetweenLastMonth( Date monthStart, Date endMonth);

    ProductDTO findByItemsReceivedToday(Date dayStart,Date dayEnd);

    ProductDTO findByItemsCreatedBetweenLastWeek( Date weekStart, Date weekEnd);

    ProductDTO getProductById(long id);

    ProductDTO updateProduct(ProductDTO productDTO, long id);

    void deleteProductById(long id);

}
