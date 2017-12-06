class Card:
    faces = ["Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
             "Jack", "Queen", "King"]

    def __init__(self, val, suit):
        self.face = self.faces[val - 1]
        if(val > 10):
            self.val = 10
        else:
            self.val = val
        self.suit = suit


    def get_name(self):
        name = self.face + " of " + self.suit
        return name
