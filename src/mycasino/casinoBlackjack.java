package mycasino;

public class casinoBlackjack extends MyCasino {

    static int userTotal = 0;
    static int cardsDrawn = 2;
    static int compTotal = 0;
    static int index;

    public static void blackjack() {
        userTotal = 0;
        cardsDrawn = 2;
        compTotal = 0;
        canPlay = false;
        gameFinished = false;
        count = 0;
        usersCards.removeAll(usersCards);
        compsCards.removeAll(compsCards);
        

        System.out.println("Hello, welcome to blackjack!");

        System.out.println("Let's begin!");
        index = searchForAccount();

        if (index == -1) {

            System.out.println("We could not find a account matching that number");

        } else {

            while (canPlay == false) {

                System.out.println("How many Wonka Coins would you like to bet?");
                usersBet = 0;
                usersBet = input.nextInt();
                System.out.println(usersBet);

                double userBalance = allAccounts.get(index).getBalance();

                int wonkaCoins = (int) (userBalance);

                double moneyAvailable = wonkaCoins - usersBet;
                if (moneyAvailable <= 0) {
                    System.out.println("You do not have enough money for that bet");
                    System.out.println("You have a total of £" + allAccounts.get(index).getBalance() + " in your account");
                    System.out.println("So you have " + (allAccounts.get(index).getBalance()) + " Wonka coins");
                } else {
                    canPlay = true;
                }
            }
            computersBet = rand.nextInt((max - min) + min) + 1;
            System.out.println("I will bet " + computersBet + " Wonka coins");

            System.out.println("Do you need to hear the rules? (y/n)");
            String rulesNeeded = input.next();

            if (rulesNeeded.equalsIgnoreCase("y")) {
                blackjackRules();
            }

            System.out.println("Let us begin");

            usersCards.add(cards[count]);
            count++;
            usersCards.add(cards[count]);
            count++;

            while (gameFinished == false && cardsDrawn < 10) {
                userTotal = 0;
                cardsSetUp();
                System.out.println("Your cards are: ");
                for (int i = 0; i < usersCards.size(); i++) {
                    System.out.print(usersCards.get(i) + " ");
                }

                System.out.println("Would you like to draw or play? (d/p)");
                String userChoice = input.next();

                if (userChoice.equalsIgnoreCase("d")) {
                    usersCards.add(cards[count]);
                    count++;
                    cardsDrawn++;
                    for (int i = 0; i < usersCards.size(); i++) {
                        userTotal = usersCards.get(i) + userTotal;
                    }

                } else if (userChoice.equalsIgnoreCase("p")) {
                    if (userTotal > 21) {
                        overflow();
                        gameFinished = true;
                        break;

                    }
                    if (userTotal == 21) {
                        equalsTwentyOne();
                        gameFinished = true;
                        break;

                    }
                    play();
                    gameFinished = true;
                    break;
                }

                if (cardsDrawn == 10) {
                    outOfRounds();
                    gameFinished = true;
                    break;
                }

            }
        }
    }

    public static void play() {
        for (int i = 0; i < usersCards.size(); i++) {
            userTotal = usersCards.get(i) + userTotal;
        }
        System.out.println("Your cards totalled to " + userTotal);

        cardsSetUp();
        int randomNoOfCards = rand.nextInt(5);
        for (int i = 0; i < randomNoOfCards; i++) {
            compsCards.add(cards[i]);
        }

        for (int i = 0; i < compsCards.size(); i++) {
            compTotal = compsCards.get(i) + compTotal;
        }

        System.out.println("My cards totalled to " + compTotal);

        if (compTotal > 21) {
            System.out.println("I have overflowed");
            winner();
        }

        if (userTotal > compTotal) {
            winner();

        } else if (compTotal > userTotal) {
            loser();

        } else if (compTotal == userTotal) {
            System.out.println("We have made the same amount");
            System.out.println("Here I shall add both our bets and half them");
            System.out.println("You shall be given one half into your account");

            double userBalance = allAccounts.get(index).getBalance();
            userBalance = userBalance + ((usersBet + computersBet) / 2);
            allAccounts.get(index).setBalance(userBalance);

            System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());
        }
    }

    public static void overflow() {
        System.out.println("Your cards are: ");
        for (int i = 0; i < usersCards.size(); i++) {
            System.out.print(usersCards.get(i) + " ");
        }

        System.out.println("You have overflowed!");
        System.out.println("You lose");
        loser();
    }

    public static void equalsTwentyOne() {
        System.out.println("Your cards are: ");
        for (int i = 0; i < usersCards.size(); i++) {
            System.out.print(usersCards.get(i) + " ");
        }
        System.out.println("Well done! You have got exactly 21!");
        System.out.println("You win");
        winner();
    }

    public static void outOfRounds() {
        for (int i = 0; i < usersCards.size(); i++) {
            userTotal = usersCards.get(i) + userTotal;
        }

        System.out.println("You have run out of cards to draw. Your total has come to " + userTotal);
        cardsSetUp();
        int randomNoOfCards = rand.nextInt(5);
        for (int i = 0; i < randomNoOfCards; i++) {
            compsCards.add(cards[i]);
        }

        for (int i = 0; i < compsCards.size(); i++) {
            compTotal = compsCards.get(i) + compTotal;
        }

        System.out.println("My cards totalled to " + compTotal);
        if (compTotal > 21) {
            System.out.println("I have overflowed");
            winner();
        }

        if (userTotal > compTotal) {
            winner();
            
        } else if (compTotal > userTotal) {
            loser();
            
        } else if (compTotal == userTotal) {
            System.out.println("We have made the same amount");
            System.out.println("Here I shall add both our bets and half them");
            System.out.println("You shall be given one half into your account");

            double userBalance = allAccounts.get(index).getBalance();
            userBalance = userBalance + ((usersBet + computersBet) / 2);
            allAccounts.get(index).setBalance(userBalance);

            System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());
        }

    }

    public static void loser() {
        System.out.println("I have won");
        System.out.println("You have lost " + (computersBet + usersBet) + " Wonka Coins");
        System.out.println("Better luck next time!");

        double userBalance = allAccounts.get(index).getBalance();

        userBalance = userBalance - (usersBet + computersBet);

        allAccounts.get(index).setBalance(userBalance);

        System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());
    }

    public static void winner() {
        System.out.println("You have won all my " + computersBet + " Wonka Coins");
        System.out.println("Well done");

        double userBalance = allAccounts.get(index).getBalance();

        userBalance = userBalance + computersBet;

        allAccounts.get(index).setBalance(userBalance);

        System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());
    }

    public static void blackjackRules() {
        System.out.println("");
        System.out.println("The rules for blackjack are simple");
        System.out.println("AIM:");
        System.out.println("The aim of the game is to get cards that add up as close to or on 21");
        System.out.println("PLAY:");
        System.out.println("- We each have a starting bet");
        System.out.println("- We then each get given two cards to begin with");
        System.out.println("- You can then ask the dealer if you want to have another card");
        System.out.println("  or if you think its too risky you can play your cards");
        System.out.println("- You may only draw 10 cards");
        System.out.println("- However be careful not to get over 21, as you will lose!");
        System.out.println("GOOD LUCK AND HAVE FUN!");
        System.out.println("");
        
    }

}
