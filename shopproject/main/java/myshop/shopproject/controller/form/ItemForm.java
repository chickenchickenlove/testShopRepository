package myshop.shopproject.controller.form;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Min(0)
    private Integer price;

    @NotNull
    @Min(0)
    private Integer stockQuantity;

    @NotEmpty
    private String author;
    @NotEmpty
    private String isbn;







}
