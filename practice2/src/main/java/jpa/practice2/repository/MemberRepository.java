package jpa.practice2.repository;

import jpa.practice2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery(
                "select m from Member m where m.name = :name", Member.class
        )
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery(
                "select m from Member m", Member.class
        )
                .getResultList();
    }


}
