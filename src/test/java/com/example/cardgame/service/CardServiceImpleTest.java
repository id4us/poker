package com.example.cardgame.service;

import com.example.cardgame.model.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceImpleTest {

    @Autowired
    private CardService cardService;

    @Test
    public void testPairExists() {

        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new Card(1, "spade"));
        cards.add(new Card(2, "heart"));
        cards.add(new Card(3, "spade"));
        cards.add(new Card(1, "diamond"));
        cards.add(new Card(4, "spade"));

        assertThat(cardService.getCombination(cards), is("PAIR"));

    }


    @Test
    public void testStraightFlush() {

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1, "spade"));
        cards.add(new Card(3, "spade"));
        cards.add(new Card(2, "spade"));
        cards.add(new Card(4, "spade"));
        cards.add(new Card(5, "spade"));
        assertThat(cardService.getCombination(cards), is("STRAIGHTFLUSH"));

    }

    @Test
    public void testStraight() {

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1, "spade"));
        cards.add(new Card(3, "spade"));
        cards.add(new Card(2, "diamond"));
        cards.add(new Card(4, "spade"));
        cards.add(new Card(5, "heart"));
        assertThat(cardService.getCombination(cards), is("STRAIGHT"));

    }

    @Test
    public void testFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(5, "spade"));
        cards.add(new Card(5, "heart"));
        cards.add(new Card(6, "diamond"));
        cards.add(new Card(6, "spade"));
        cards.add(new Card(5, "heart"));
        assertThat(cardService.getCombination(cards), is("FULLHOUSE"));

    }

    @Test
    public void testFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(5, "spade"));
        cards.add(new Card(4, "spade"));
        cards.add(new Card(6, "spade"));
        cards.add(new Card(6, "spade"));
        cards.add(new Card(5, "spade"));
        assertThat(cardService.getCombination(cards), is("FLUSH"));

    }


}