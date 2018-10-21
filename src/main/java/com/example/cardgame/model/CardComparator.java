package com.example.cardgame.model;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getNumber()-o2.getNumber();
    }
}
