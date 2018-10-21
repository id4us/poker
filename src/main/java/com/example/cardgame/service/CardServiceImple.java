package com.example.cardgame.service;

import com.example.cardgame.model.Card;
import com.example.cardgame.model.CardComparator;
import com.example.cardgame.model.Combinations;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Data
@NoArgsConstructor
public class CardServiceImple implements CardService {

    @Override
    public String getCombination(ArrayList<Card> cards) {

        if (checkStraightFlush(cards)) {
            return Combinations.STRAIGHTFLUSH.toString();
        }
        if (checkStraight(cards)) {
            return Combinations.STRAIGHT.toString();
        }
        if(checkFlush(cards)){
            return Combinations.FLUSH.toString();
        }
        if(checkFullHouse(cards)){
            return Combinations.FULLHOUSE.toString();
        }
        if (checkPair(cards)) {
            return Combinations.PAIR.toString();
        }
        return Combinations.HIGHCARD.toString();
    }

    private static Boolean checkFlush(ArrayList<Card> cards) {
        return cards.stream().allMatch(isSameColor(cards.get(0)));

    }

    private static Boolean checkFullHouse(ArrayList<Card> cards) {

        Boolean fullHouse=false;
        ArrayList<Card> cards1 = sortCards(cards);

        if(isFullHouse(cards)){
            fullHouse=true;
        }
        return fullHouse;
    }


    private static Boolean isFullHouse(ArrayList<Card> cards) {

        final Map<Integer, List<Card>> map = cards.stream()
                .collect(Collectors.groupingBy(s -> s.getNumber()));
        if(map.size()==2){
            return true;
        }
        return false;
    }

    private static Boolean checkStraightFlush(ArrayList<Card> cards) {

        Boolean straightFlush = false;

        if (checkStraight(cards)) {
            String type = cards.get(0).getType();
            straightFlush = cards.
                    stream().
                    allMatch(isSameColor(cards.get(0)));
            return straightFlush;

        }

        return false;
    }

    private static boolean checkStraight(ArrayList<Card> cards) {

        ArrayList<Card> sortedCards = sortCards(cards);
        Boolean straightExists = false;

        for (int i = 0; i < sortedCards.size() - 1; i++) {

            if (sortedCards.get(i).getNumber() == sortedCards.get(i + 1).getNumber() - 1) {
                straightExists = true;
            } else {
                straightExists = false;
                break;
            }
        }
        return straightExists;
    }

    private static Boolean checkPair(ArrayList<Card> cards) {

        ArrayList<Card> sortedCards = sortCards(cards);

        Boolean pairExists = false;

        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i).getNumber() == sortedCards.get(i + 1).getNumber()) {
                pairExists = true;
                break;
            }

        }

        return pairExists;
    }

    public static ArrayList<Card> sortCards(ArrayList<Card> cards) {
        cards.sort(new CardComparator());
        return cards;
    }

    private static Predicate<Card> isSameColor(Card card) {
        return p -> p.getType().equals(card.getType());
    }


}
