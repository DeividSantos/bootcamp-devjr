package com.abutua.productbackend.dto;

import javax.validation.constraints.Min;

public class IntegerDTO {

    @Min(value = 1, message = "Min value 1")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
