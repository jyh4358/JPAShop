package jpa.practice.domain.item;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Album extends Item{


    private String artist;
    private String etc;



}
