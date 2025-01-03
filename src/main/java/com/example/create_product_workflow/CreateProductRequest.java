package com.example.create_product_workflow;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateProductRequest implements Serializable {
    private String productName;
    private String productDesc;
    private BigDecimal price;
}
