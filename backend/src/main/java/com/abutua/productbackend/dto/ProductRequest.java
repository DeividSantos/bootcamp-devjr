package com.abutua.productbackend.dto;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductRequest {

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name length min=3 and max=255")
    private String name;

    @NotBlank(message = "Description can not be blank")
    @Size(min = 3, max = 1024, message = "Description length min=3 and max=")
    private String description;

    private boolean promotion;

    private boolean newProduct;

    @Min(value = 0, message = "Price min value = 0")
    private double price;

    @NotNull
    @Valid
    private IntegerDTO category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IntegerDTO getCategory() {
        return category;
    }

    public void setCategory(IntegerDTO category) {
        this.category = category;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setNewProduct(newProduct);
        product.setPromotion(promotion);
        product.setCategory(new Category(category.getId()));

        return product;
    }

}
