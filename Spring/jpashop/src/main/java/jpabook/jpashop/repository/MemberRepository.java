package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    private EntityManager em;
    // 스프링 Data Jpa를 사용하 Entity Manager도 주입해준다.(RequiredArgsConstructor 어노테이션)
    private final EntityManager em;


    // save
    public void save(Member member) {
        em.persist(member);
    }

    // find by id
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // find all
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // find by name
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
