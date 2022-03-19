package jpa.practice2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    @DisplayName("멤버 저장 테스트")
    public void memberSaveTest() {
        Member member1 = new Member("member1", new Address("서울", "노원구", "111"));
        Member member2 = new Member("member2", new Address("서울", "노원구", "111"));

        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        Assertions.assertThat(em.find(Member.class, member1.getId()).getId()).isEqualTo(member1.getId());
        Assertions.assertThat(em.find(Member.class, member1.getId()).getName()).isEqualTo(member1.getName());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("멤버 조회 테스트")
    public void memberFindTest() {
        Member member1 = new Member("member1", new Address("서울", "노원구", "111"));
        Member member2 = new Member("member2", new Address("서울", "노원구", "111"));
        Member member3 = new Member("member3", new Address("경기도", "파주", "111"));


        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        List<Member> resultList = em.createQuery(
                        "select m from Member m where m.address.city = :city", Member.class
                )
                .setParameter("city", "서울")
                .getResultList();

        Assertions.assertThat(resultList.size()).isEqualTo(2);
    }
}