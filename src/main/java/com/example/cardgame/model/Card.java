package com.example.cardgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class Card implements Comparable<Card>{

    Integer number;
    String type;

    @Override
    public int compareTo(Card o) {
        return this.number - o.getNumber();
    }
}
