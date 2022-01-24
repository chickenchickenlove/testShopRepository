package myshop.shopproject.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {

    private Long memberId;
    private Long itemId;
    @NotNull
    private Integer count;

}
