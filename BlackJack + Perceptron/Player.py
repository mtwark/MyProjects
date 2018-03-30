from Deck import Deck

class Player:

    def __init__(self, name):
        self.name = name
        self.cards = []
        self.upper_score = 0
        self.lower_score = 0

    def add_card(self, add_card):
        self.cards += [add_card]
        if add_card.val == 1:
            self.upper_score += 11
            self.lower_score += 1
        else:

            self.upper_score += add_card.val
            self.lower_score += add_card.val

    def collect_hand(self):
        self.score = 0
        hand = self.cards
        self.cards = []
        self.lower_score = 0
        self.upper_score = 0
        return hand

    def print_hand(self):
        for card in self.cards:
            print(card.get_name())

    def get_sum(self):
        ace = False
        for card in self.cards:
            if card.val == 1:
                ace = True
        if ace:
            return str(self.lower_score) + " or " + str(self.upper_score)
        else:
            return str(self.lower_score)

