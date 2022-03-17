package jpa.practice2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestMemberTest {

    @Test
    public void test() {

        TestMember member = TestMember.builder()
                .name("name")
                .age("age")
                .build();

        System.out.println("member = " + member);

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
                    TestMember.builder()
                            .name("name2")
                            .build();
                });

    }
}