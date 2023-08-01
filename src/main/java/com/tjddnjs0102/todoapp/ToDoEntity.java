package com.tjddnjs0102.todoapp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 255)
    private String title;

    private Integer todoOrder;

    private Boolean completed;

}
