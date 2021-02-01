package mycasino;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MyCasino {

    static Scanner input = new Scanner(System.in);
    static int accountNos = 4567;
    static ArrayList<userAccounts> allAccounts = new ArrayList<>();
    static boolean gameFinished = false;
    static int max = 20;
    static int min = 10;
    static Random rand = new Random();
    static int cardsDrawn;
    static boolean overflow = false;
    static boolean compOverflow = false;
    static int rounds = 10;
    static int deaths = 0;
    static String guess;
    static int[] cards = new int[10];
    static int cardsTotal = 0;
    static int computersBet = 0;
    static int usersBet = 0;
    static ArrayList<Integer> usersCards = new ArrayList<>();
    static ArrayList<Integer> compsCards = new ArrayList<>();
    static int count = 0;
    

    static boolean exit = false;
    static boolean canPlay = false;

    public static void main(String[] args) {

        greeting();

    }

    public static void greeting() {
        while (exit == false) {
            System.out.println("Hello welcome to Ailise's Casino!");
            System.out.println("What would you like to do?");
            System.out.println("1 - Play a game");
            System.out.println("2 - Create an account");
            System.out.println("3 - View my account");
            System.out.println("4 - Add money to my account");
            System.out.println("5 - Out of money");
            System.out.println("0 - Exit casino");
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    chooseAGame();
                    break;

                case 2:
                    createAccount();
                    break;

                case 3:
                    viewAccount();
                    break;

                case 4:
                    addMoney();
                    break;
                
                case 5:
                    outOfMoney();
                    break;

                case 0:
                    System.out.println("Thank you for coming by Ailise's Casino");
                    exit = true;
            }
        }

    }

    public static void chooseAGame() {
        System.out.println("Welcome to choosing a game");
        System.out.println("Here, the currency is Wonka Coins");
        System.out.println("In each game you use Wonka Coins to bet and win");
        System.out.println("You will be playing against me in each round");
        System.out.println("These are the choices of games:");
        System.out.println("1 - BlackJack");
        System.out.println("2 - Higher or Lower");
        System.out.println("3 - Guess the Number");
        int userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                casinoBlackjack.blackjack();
                break;
            case 2:
                casinoHigherOrLower.higherOrLower();
                break;
            case 3:
                casinoGuessTheNumber.numberGuessing();
                break;
        }

    }

    public static void cardsSetUp() {
        // 10 cards
        for (int i = 0; i < 10; i++) {
            cards[i] = rand.nextInt(14);

        }

    }

    public static void createAccount() {
        System.out.println("Welcome to creating an account");
        System.out.println("First enter your name");
        input.next();
        String name = input.nextLine();
        System.out.println("How much money would you like to put into your account to start with?");
        System.out.println("1 Wonka coin is £1");
        int startingMoney = input.nextInt();
        double balance = startingMoney;
        int wonkaCoins = startingMoney;

        System.out.println("That translates to " + wonkaCoins + " Wonka coins");

        int accountNumber = accountNos;
        userAccounts account = new userAccounts(name, balance, accountNumber);
        allAccounts.add(account);
        accountNos++;

        System.out.println("Perfect, thank you");
        System.out.println("Below are your account details:");
        System.out.println(account.toString());
        System.out.println("");

        //include a passwrod
        //outside of search for account
        //ask them to enter their password
        //if password does not equal to .getpassword(index)
        //then say they cannot play or ask them to reenter until they exit
        //use a while loop and if loop
        //they can then choose to leave if they enter e.g. leave or something 
        greeting();

    }

    public static void viewAccount() {
        if (allAccounts.isEmpty()) {
            System.out.println("Sorry there are no accounts");
            greeting();

        } else {

            int index = searchForAccount();

            if (index == -1) {
                System.out.println("I could not find any matching accounts");
            } else {
                System.out.println(allAccounts.get(index));
            }

        }

    }

    public static void addMoney() {
        int index = searchForAccount();

        if (index == -1) {
            System.out.println("There are no accounts matching that number");
        } else {

            double userBalance = allAccounts.get(index).getBalance();

            System.out.println("How much would you like to add to your account?");
            int addMoney = input.nextInt();

            allAccounts.get(index).setBalance(userBalance + addMoney);

            System.out.println("You now have £" + allAccounts.get(index).getBalance() + " in your account");

        }

    }
    
    public static void outOfMoney(){
       int index = searchForAccount();

        if (index == -1) {
            System.out.println("There are no accounts matching that number");
        } else {

            double userBalance = allAccounts.get(index).getBalance();
            if(userBalance > 0){
                System.out.println("You are not out of money");
            }

            if(userBalance <= 0){
                System.out.println("We are granting you Wonka Coins for you to win your money back");
                double amountMissing = 0.00;
                amountMissing = (0 - userBalance);
                

                allAccounts.get(index).setBalance(amountMissing + 10);

            System.out.println("You now have £" + allAccounts.get(index).getBalance() + " in your account");

        }
    }
    }

    public static int searchForAccount() {
        System.out.println("Please enter your account number");
        int enteredAccountNumber = input.nextInt();

        for (int i = 0; i < allAccounts.size(); i++) {
            if (enteredAccountNumber == (allAccounts.get(i).getAccountNumber())) {
                return i;

            }
        }
        return -1;
    }
}
