package myshop.shopproject.repository;

import lombok.Data;
import myshop.shopproject.domain.OrderStatus;

@Data
public class OrderSearch {

    private String username;
    private OrderStatus orderStatus;


}
