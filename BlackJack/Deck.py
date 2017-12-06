from Card import Card
import numpy as np

class Deck:
    suits = ["Spades", "Clubs", "Hearts", "Diamonds"]
    cards = []

    def __init__(self, cards_in_deck):
        self.cards_in_deck = cards_in_deck
        for suit in self.suits:
            for val in range(1, 14):
                self.cards += [Card(val, suit)]

    def get_top(self):
        self.cards_in_deck -= 1
        return self.cards[self.cards_in_deck]

    def add_card(self, add_card):
        self.cards_in_deck += 1
        self.cards += [add_card]

    def add_cards(self, add_cards):
        self.cards_in_deck += len(add_cards)
        self.cards += add_cards

    def shuffle(self, swaps):
        for i in range(swaps):
            index1 = np.random.randint(0, self.cards_in_deck)
            index2 = np.random.randint(0, self.cards_in_deck)
            temp = self.cards[index1]
            self.cards[index1] = self.cards[index2]
            self.cards[index2] = temp

    def print_deck(self):
        for card in self.cards:
            print(card.get_name())

