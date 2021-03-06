package myshop.shopproject.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public static Item createItem(String name, int stockQuantity, int price, String author, String isbn) {

        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        book.setAuthor(author);
        book.setIsbn(isbn);

        return book;
    }


    //== 비즈니스 로직 ==//

    public void addStock(int count) {
        stockQuantity += count;
    }

    public void removeStock(int count) {
        int result = stockQuantity - count;

        // 실패 로직
        if (result < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }

        // 성공 로직
        setStockQuantity(result);
    }


}
