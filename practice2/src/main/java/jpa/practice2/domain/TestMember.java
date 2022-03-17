package jpa.practice2.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestMember {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String age;

    @Builder

    public TestMember(String name, String age) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(age, "age must not be empty");

        this.name = name;
        this.age = age;
    }
}
