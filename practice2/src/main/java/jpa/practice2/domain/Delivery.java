package jpa.practice2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
    private Order oder;

    @Embedded
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus status;

    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }

    public void insertOrder(Order order) {
        this.oder = order;
    }
}
