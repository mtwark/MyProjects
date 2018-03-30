# Deck.py
# Deck class that is pretty much just a list of Card objects. Contains functions to
# shuffle, add cards, take top card, and print every card in the deck.

from Card import Card
import numpy as np

class Deck:
    suits = ["Spades", "Clubs", "Hearts", "Diamonds"]
    cards = []

    # Initialized with given amount of cards in the deck with values from 1-13
    def __init__(self, cards_in_deck):
        self.cards_in_deck = cards_in_deck
        for suit in self.suits:
            for val in range(1, 14):
                self.cards += [Card(val, suit)]

    # Returns the top card in the deck and decrements the number of cards by one.
    # Considered using pop() here to have it actually remove the value from the list,
    # but it caused issues with something else and figured this would be fine as long
    # as I keep the cards_in_deck properly updated.
    def get_top(self):
        self.cards_in_deck -= 1
        return self.cards[self.cards_in_deck]

    # Add one card to the deck and increment cards_in_deck by one.
    def add_card(self, add_card):
        self.cards_in_deck += 1
        self.cards += [add_card]

    # Add a list of cards to the deck and increment by the length of the list
    def add_cards(self, add_cards):
        self.cards_in_deck += len(add_cards)
        self.cards += add_cards

    # Swap two random cards in the deck a given amount of times.
    def shuffle(self, swaps):
        for i in range(swaps):
            index1 = np.random.randint(0, self.cards_in_deck)
            index2 = np.random.randint(0, self.cards_in_deck)
            temp = self.cards[index1]
            self.cards[index1] = self.cards[index2]
            self.cards[index2] = temp

    # Print every card in the deck
    def print_deck(self):
        for card in self.cards:
            print(card.get_name())
