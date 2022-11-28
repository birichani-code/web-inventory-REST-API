package com.birichani.code.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProductDTO {

    private long id;

    // name should not be null  or empty
    // name should have at least 2 characters
    @NotEmpty
    @Size(min = 3, message = "product name should have at least 3 characters")
    private String name;
    @NotEmpty
    private Date createdTime;


}
