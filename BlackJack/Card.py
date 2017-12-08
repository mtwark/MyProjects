# Card.py
# Pretty self explanatory class. Used to hold information relevant to playing cards.

class Card:
    faces = ["Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
             "Jack", "Queen", "King"]

    # Takes in the value of the card, from 1-13, and a suit.
    # If the value is over 10, leave it as 10
    def __init__(self, val, suit):
        self.face = self.faces[val - 1]
        if(val > 10):
            self.val = 10
        else:
            self.val = val
        self.suit = suit

    # Returns a string in this format: Ace of Spades
    def get_name(self):
        name = self.face + " of " + self.suit
        return name
