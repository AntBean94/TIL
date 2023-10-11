package jpabook.jpashop.service;

import jpabook.jpashop.domain.items.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경감지와 병합(merge)
     *
     * 영속상태의 엔티티는 변경감지가 가능, 변경사항 더티체킹, 부분변경 가능
     * 준영속상태의 엔티티는 merge를 통해 영속상태로 변환, 변경감지 X, 모든 필드에대해 업데이트된다.
     */
    @Transactional
    public void updateItem(Long itemId, ItemUpdateDTO dto) {
        // 식별자를 통해 영속상태의 엔티티 조회 => 변경감지 가능
        Item item = itemRepository.findOne(itemId);
        item.change(dto);
        // 트랜잭션이 종료될 때 DB로 flush() 호출하여 변경사항 update
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }


}
