package mycasino;

public class casinoHigherOrLower extends MyCasino {

    public static void higherOrLower() {
        usersBet = 0;
        canPlay = false;
        deaths = 0;
        rounds = 10;

        System.out.println("Let's begin!");
        int index = searchForAccount();

        if (index == -1) {

            System.out.println("We could not find a account matching that number");

        } else {

            while (canPlay == false) {

                System.out.println("How many Wonka Coins would you like to bet?");
                usersBet = input.nextInt();

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
            System.out.println("I will bet " + computersBet + " Wonka Coins");

            //check they have enough money in their account using method in blackjack
            System.out.println("Do you need to hear the rules? (y/n)");
            String rulesNeeded = input.next();

            if (rulesNeeded.equalsIgnoreCase("y")) {
                higherOrLowerRules();

            }
            cardsSetUp();

            System.out.println("The game is about to begin");

            while ((deaths < 3) && (rounds > 0)) {
                for (int i = 0; i < 10; i++) {
                    if (i < 9) {
                        System.out.println("THE CARD IS " + cards[i]);

                        System.out.println("Would you like to go higher or lower?");
                        guess = input.next();

                        if (cards[i] > cards[i + 1]) {
                            if (guess.equalsIgnoreCase("higher")) {
                                System.out.println("Sorry thats incorrect");
                                deaths++;
                                rounds--;

                            } else if (guess.equalsIgnoreCase("lower")) {
                                System.out.println("Well done thats correct");
                                rounds--;

                            }
                        }
                        if (cards[i] <= cards[i + 1]) {
                            if (guess.equalsIgnoreCase("lower")) {
                                System.out.println("Sorry thats incorrect");
                                deaths++;
                                rounds--;

                            } else if ((guess.equalsIgnoreCase("higher")) || (cards[i] == cards[i + 1])) {
                                System.out.println("Well done thats correct");
                                rounds--;

                            }
                        }

                        if (cards[i] == cards[i + 1]) {

                        }
                    }
                    if (i == 9) {
                        System.out.println("THE CARD IS " + cards[8]);

                        System.out.println("Would you like to go higher or lower?");
                        guess = input.next();

                        if (cards[8] > cards[9]) {
                            if (guess.equalsIgnoreCase("higher")) {
                                System.out.println("Sorry thats incorrect");
                                rounds--;
                                deaths++;

                            } else if (guess.equalsIgnoreCase("lower")) {
                                System.out.println("Well done thats correct");
                                rounds--;
                            }
                        }
                        if (cards[8] < cards[9]) {
                            if (guess.equalsIgnoreCase("lower")) {
                                System.out.println("Sorry thats incorrect");
                                rounds--;
                                deaths++;
                            } else if (guess.equalsIgnoreCase("higher")) {
                                System.out.println("Well done thats correct");
                                rounds--;
                                deaths++;
                            }
                        }
                    }
                    if (deaths == 3) {
                        break;
                    }
                }
            }
            if (deaths == 3) {
                System.out.println("Sorry, you died too many times!");
                System.out.println("You have lost " + (usersBet + computersBet) + " Wonka Coins");
                //get balance from index
                //subtract computers bet and usersbet from balance
                double userBalance = allAccounts.get(index).getBalance();

                userBalance = userBalance - (usersBet + computersBet);

                allAccounts.get(index).setBalance(userBalance);
                System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());

            }
            if (rounds == 0) {
                System.out.println("Congratulations, you win!");
                System.out.println("You have won all my " + computersBet + " Wonka Coins");
                System.out.println("Well done");
                //get balance from index
                //add computers bet to balance
                double userBalance = allAccounts.get(index).getBalance();

                userBalance = userBalance + computersBet;

                allAccounts.get(index).setBalance(userBalance);

                System.out.println("Your new balance is £" + allAccounts.get(index).getBalance());

            }

        }
    }

    public static void higherOrLowerRules() {
        System.out.println("Here are the rules for Higher or Lower");
        System.out.println("There are 10 rounds in total");
        System.out.println("You have 3 lives");
        System.out.println("You get given a card");
        System.out.println("You must guess whether the next card is higher or lower than the card you currently hold");
        System.out.println("If you guess it correctly you move on");
        System.out.println("If you are incorrect, you lose a life");
        System.out.println("If you lose all your lives, you lose");
    }

}
