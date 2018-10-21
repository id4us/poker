package com.example.cardgame.utils;

import com.example.cardgame.model.Card;
import com.example.cardgame.model.CardComparator;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class CheckHands {

    private Boolean checkFlush(ArrayList<Card> cards) {
        return cards.stream().allMatch(isSameColor(cards.get(0)));

    }

    private Boolean checkFullHouse(ArrayList<Card> cards) {

        Boolean fullHouse=false;
        ArrayList<Card> cards1 = sortCards(cards);

        if(isFullHouse(cards)){
            fullHouse=true;
        }
        return fullHouse;
    }


    private Boolean isFullHouse(ArrayList<Card> cards) {

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

    private Boolean checkPair(ArrayList<Card> cards) {

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

    private Card highCard(List<Card> cards){

        Comparator<Card> cardComparator = Collections.reverseOrder(new CardComparator());
        return cards.get(0);


    }



}
