package jpa.practice.service;

import jpa.practice.domain.Address;
import jpa.practice.domain.Member;
import jpa.practice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    public void saveTest() {

        Address address = new Address("서울", "노원구", "111-111");

        Member member = new Member("member1", address);

        Long saveId = memberService.join(member);

        Assertions.assertThat(memberService.findOne(saveId)).isEqualTo(member);
    }

    @Test
    @DisplayName("회원 중복 예외 테스트")
    public void saveDuplicate() {

        Address address = new Address("서울", "노원구", "111-111");
        Member member1 = new Member("member1", address);
        Member member2 = new Member("member1", address);

        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }
}