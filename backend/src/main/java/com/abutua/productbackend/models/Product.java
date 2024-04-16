package com.abutua.productbackend.models;

import com.abutua.productbackend.dto.ProductResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "TBL_PRODUCT")
public class Product implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name length min=3 and max=255")
    private String name;

    @Column(nullable = false, length = 1024)
    @NotBlank(message = "Description can not be blank")
    @Size(min = 3, max = 1024, message = "Description length min=3 and max=")
    private String description;

    private boolean promotion;

    private boolean newProduct;

    @Min(value = 0, message = "Price min value = 0")
    private Double price;

    @ManyToOne
    @Valid
    private Category category;

    // Métodos Construtores
    public Product() {
    }

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, String description, Category category, boolean promotion, boolean newProduct,
                   double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
    }


    // Métodos
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", promotion=" + promotion
                + ", newProduct=" + newProduct + ", price=" + price + "]";
    }

    public ProductResponse toDTO() {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(id);
        productResponse.setName(name);
        productResponse.setDescription(description);
        productResponse.setPrice(price);
        productResponse.setNewProduct(newProduct);
        productResponse.setPromotion(promotion);
        productResponse.setCategory(category.toDTO());

        return productResponse;
    }


}
