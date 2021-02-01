package mycasino;

public class casinoGuessTheNumber extends MyCasino {

    public static void numberGuessing() {
        canPlay = false;
        rounds = 1;
        usersBet = 0;
        gameFinished = false;

        System.out.println("Let's begin!");
        int index = searchForAccount();

        if (index == -1) {

            System.out.println("We could not find a account matching that number");

        } else {

            while (canPlay == false) {
                System.out.println("The stakes are higher in this game, so you must bet at least 10 wonka coins");

                System.out.println("So, how many Wonka Coins would you like to bet?");

                usersBet = input.nextInt();

                double userBalance = allAccounts.get(index).getBalance();

                int wonkaCoins = (int) (userBalance);

                double moneyAvailable = wonkaCoins - usersBet;
                if (moneyAvailable <= 0) {
                    System.out.println("You do not have enough money for that bet");
                    System.out.println("You have a total of £" + allAccounts.get(index).getBalance() + " in your account");

                } else if (usersBet < 10) {
                    System.out.println("You must bet higher");

                } else {
                    canPlay = true;
                }
            }

            computersBet = rand.nextInt((30 - 10) + 1) + 10;
            System.out.println("I will bet " + computersBet + " Wonka Coins");

            System.out.println("Would you like to hear the rules?");
            String rulesNeeded = input.next();

            if (rulesNeeded.equalsIgnoreCase("y")) {
                guessTheNumberRules();
            }

            cardsSetUp();
            int random = rand.nextInt(10);

            int guessTheCard = cards[random];

            while (gameFinished == false) {

                while (rounds < 6) {
                    System.out.println("Round " + rounds + ":");
                    System.out.println("What is your guess?");
                    int userGuess = input.nextInt();

                    if (userGuess > guessTheCard) {
                        System.out.println("Lower");
                        rounds++;

                    } else if (userGuess < guessTheCard) {
                        System.out.println("Higher");
                        rounds++;
                    }

                    if (rounds == 6) {
                        System.out.println("I'm afraid you have run out of chances");
                        System.out.println("The card to guess was " + guessTheCard);
                        System.out.println("You have lost " + (usersBet + computersBet) + " Wonka Coins");

                        double userBalance = allAccounts.get(index).getBalance();
                        userBalance = userBalance - usersBet - computersBet;
                        allAccounts.get(index).setBalance(userBalance);

                        System.out.println("Your new balance is " + userBalance);
                        gameFinished = true;
                        break;

                    } else if (userGuess == guessTheCard) {
                        System.out.println("Congratulations you have won!");
                        System.out.println("You have won " + computersBet + " Wonka Coins");

                        double userBalance = allAccounts.get(index).getBalance();
                        userBalance = userBalance + computersBet;
                        allAccounts.get(index).setBalance(userBalance);
                        
                        System.out.println("Your new account balance is £" + userBalance);
                        gameFinished = true;
                        break;
                    }
                }
            }

        }

    }
    public static void guessTheNumberRules() {
        System.out.println("Rules for guess the number:");
        System.out.println("- The computer draws a random card (0-13)");
        System.out.println("- You are given 6 chances to guess the card drawn");
        System.out.println("- We will give you clues such as higher or lower");
        System.out.println("- If you do not guess the card by the time your 6 guesses are up");
        System.out.println("- Then you lose your bet");
    }
}
