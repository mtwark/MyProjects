# Main.py
# This is the actual Blackjack game, defined mostly in functions, and then executed in the main
# block below. The program works by first creating a Deck object filled with a given number of
# Card objects, intitializing the list of Player objects, and creating a Neural Net object.
# Training data is then produced for the Network and then the Network is trained in order to
# make predictions as to whether or not hitting will cause the user to bust. These suggestions
# are displayed to the user during each decision with a confidence interval.

from Player import Player
from Deck import Deck
from NN import NN
import numpy as np


# Deals two cards to every player at the table from the top of the deck
def deal(players, deck):
    for player in players:
        player.add_card(deck.get_top())
        player.add_card(deck.get_top())


# Prints the users cards only, as well as the sum of the cards in hand
def print_my_hand(players):
    ace = False
    for card in players[0].cards:
        if card.val == 1:
            ace = True
    print("You have:")
    players[0].print_hand()
    if ace:
        print("Sum:", players[0].lower_score, "or", players[0].upper_score)
    else:
        print("Sum:", players[0].lower_score)
    print()


# Prints the dealers face up cards, as well as a sum of the face up cards
def print_dealer_hand(players, start_index):
    print("The Dealer's visible cards are:")
    ace = False
    for card in players[1].cards[start_index:]:
        if card.val == 1:
            ace = True
        print(card.get_name())
    if start_index == 0:
        if ace:
            print("Sum:", players[1].lower_score, "or",
                  players[1].upper_score)
        else:
            print("Sum:", players[1].lower_score)
    else:

        if ace:
            print("Sum:", players[1].lower_score - players[1].cards[0].val, "or", players[1].upper_score - players[1].cards[0].val)
        else:
            print("Sum:", players[1].lower_score - players[1].cards[0].val)
    print()


# Prints every card that is in the hand of a player at the table
def print_all_hands(players):
    for player in players[2:]:
        print(player.name)
        player.print_hand()
        ace = False
        for card in player.cards:
            if card.val == 1:
                ace = True
        if ace:
            print("Sum:", player.lower_score, "or", player.upper_score)
        else:
            print("Sum:", player.lower_score)
        print()


# Function that determines and prints the winners. The dealer_score var will be 0 if bust.
# Checks every player against the dealers highest score under 21, and prints who won.
def get_winners(players):
    dealer_score = 0
    if players[1].upper_score > 21:
        players[1].upper_score = players[1].lower_score
        if players[1].upper_score > 21:
            print("The dealer bust!\n")
            dealer_score = 0
    else:
        dealer_score = players[1].upper_score
        print("The dealer ended up with", dealer_score)

    print("\nTable Winners Summary:")
    for player in players:
        if player.name == "Dealer":
            continue
        if dealer_score == 21:
            if player.name != "Dealer" and (player.upper_score == 21 or player.lower_score == 21):
                print(player.name, "pushed with the dealer!", "\n")
        elif player.upper_score > 21:
            player.upper_score = player.lower_score
        elif player.upper_score == 21:
            print(player.name, "wins with Blackjack!")
        elif dealer_score < player.upper_score < 21 :
            print(player.name, "is a winner!")
        elif dealer_score == player.upper_score < 21:
            print(player.name, "pushed with the dealer!")



# Main game function. Starts every round by shuffling the cards in the deck. The players are then all
# Dealt their cards and the visible cards are output. The game will then inform the user of their
# cards and the sum of those cards, and give them the option to hit or stand, along with a suggestion
# from the trained neural network. This loop will continue until the user decides to stand, or they bust.
# At the end of the round, all winners are displayerd, and all cards are collected from the players and
# added back into the deck.
def play_round(players, deck, n1):
    deck.shuffle(1000)
    deal(players, deck)
    print("\nHands at the table are:")
    print_all_hands(players)
    print_dealer_hand(players, 1)
    print_my_hand(players)

    hit = True
    while hit:
        if players[0].lower_score > 21:
            print("You've bust!", "You had a sum of", players[0].lower_score)
            print()
            break

        confidence = 0
        prediction = n1.guess([players[0].lower_score/10, players[0].upper_score / 10])
        if prediction >= .5:
            suggestion = "hit."
            confidence = str(round(prediction, 2) * 100) + "% confidence."
        else:
            suggestion = "stand."
            confidence = str(100 - round(prediction, 2) * 100) + "% confidence."
        print("Neural Net suggests you", suggestion,"\n" + confidence)
        choice = input("\nWould you like to hit? (y/n)\n")

        if choice == 'n':
            break
        players[0].add_card(deck.get_top())
        print("You are dealt a", players[0].cards[-1].get_name())
        print_dealer_hand(players, 1)
        print_my_hand(players)

    while players[1].upper_score != 21 and players[1].lower_score != 21 and players[1].lower_score < 17:
        players[1].add_card(deck.get_top())
        print("The Dealer is dealt a", players[1].cards[-1].get_name())
        print_dealer_hand(players, 0)

    get_winners(players)
    for player in players:
        deck.add_cards(player.collect_hand())


# Function used in get_training_data to look at top card without taking it.
def peek_top_card(deck):
    return deck.cards[-1]


# This function produces the data used to train the neural network. The premise of this program is
# essentially to allow the neural network to cheat a bunch(hundreds of thousands) of times by
# allowing it to look at the top card in the deck to see if hitting is the right move or not. This
# function will produce a 3 x (iterations) matrix with the first 2 columns being the lower score of
# the players hand (in case of an Ace) and the second being the upper score. The third and final col
# of each row of the matrix will be either a 1 or a 0. 1 if hitting was the correct move, 0 otherwise.
def get_training_data(number_of_players, iterations):
    data = []
    players = []
    deck = Deck(52)
    for i in range(number_of_players):
        players += [Player("a")]

    for i in range(iterations):
        row = []
        shuf = np.random.randint(40, 200)
        deck.shuffle(shuf)
        deal(players, deck)
        table_sum = 0
        for player in players[2:]:
            table_sum += player.upper_score
        row += [players[0].lower_score / 10]
        row += [players[0].upper_score / 10]
        #row += [players[1].cards[-1].val / 10]
        #row += [table_sum / 10]
        hit = 0
        if peek_top_card(deck).val + players[0].upper_score <= 21:
            hit = 1
        elif peek_top_card(deck).val + players[0].lower_score <= 21 and peek_top_card(deck).val + players[0].lower_score >= players[0].upper_score:
            hit = 1
        row += [hit]
        data += [row]

        for player in players:
            deck.add_cards(player.collect_hand())

    return data



# User will control players[0]
# Dealer will be players[1]
# lowerscore, upperscore are current inputs for the neural net

deck = Deck(52)
players = []
names = ["Matt", "Dealer", "Beth", "Frank"]
for name in names:
    players += [Player(name)]

# Produce training data matrix with 4(at the moment) players at the table and n amount of rows
data = get_training_data(4, 1000)
n1 = NN(data)
n1.train(1000000)
n1.plot_cost()

# Gameplay
players[0].name = input("Please enter your name: ")
print("The players at the table are currently: ")
for player in players:
    print(player.name)
print()
playing = True
while playing:
    play_round(players, deck, n1)
    choice = input("Would you like to play again? (y/n)\n")
    if choice != 'y':
        playing = False

