package myshop.shopproject.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myshop.shopproject.domain.item.Book;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    //== 의존관계 주입==//

    private final ItemRepository itemRepository;


    //== 비즈니스 로직 ==//
    public Long saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItem(Long itemId) {
        return itemRepository.findItem(itemId);
    }

    public List<Item> findItemAll() {
        return itemRepository.findItemAll();
    }

    public void updateItem(Book item) {
        log.info("DIRTY CHECKING");

    }
}
