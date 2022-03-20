package jpa.practice2.service;

import jpa.practice2.domain.Address;
import jpa.practice2.domain.Member;
import jpa.practice2.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;


    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMember() {
        Member member = new Member("member1", new Address("서울시", "노원구", "111-111"));
        Long saveId = memberService.join(member);

        em.flush();
        em.clear();

        Member findMember = memberService.findMember(saveId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getAddress().getCity()).isEqualTo(member.getAddress().getCity());
        assertThat(findMember.getAddress().getStreet()).isEqualTo(member.getAddress().getStreet());
        assertThat(findMember.getAddress().getZipcode()).isEqualTo(member.getAddress().getZipcode());
    }

    @Test
    @DisplayName("회원 중복 테스트")
    public void DuplicateMemberTest() {
        Member member1 = new Member("member1", new Address("서울시", "노원구", "111-111"));
        Member member2 = new Member("member1", new Address("서울시", "노원구", "111-111"));

        memberService.join(member1);

        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () ->{
                memberService.join(member2);
        });
    }
}