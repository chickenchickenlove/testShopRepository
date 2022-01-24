package myshop.shopproject.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.domain.item.QItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static myshop.shopproject.domain.item.QItem.*;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    //== 외존관계 주입==//
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    /**
     * Item Repository
     * 1. 아이템 저장
     * 2. 아이템 조회
     */



    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }


    public List<Item> findItemAll() {
        return queryFactory.selectFrom(item).fetch();




    }
}
