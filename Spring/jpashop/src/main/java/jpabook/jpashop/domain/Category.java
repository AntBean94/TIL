package jpabook.jpashop.domain;

import jpabook.jpashop.domain.items.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    /**
     * 실무에서는 ManyToMany관계는 거의 사용되지 않는다.
     * 중간테이블인 category_item에 컬럼을 추가하기 어렵고 세밀하게 쿼리를 실행할 수 없기 때문이다.
     * 따라서 다대다 관례를 설정할 때는 category_item 테이블을 생성하고 각각 1:N, N:1관계를 설정하여
     * 사용한다.
     */
}
