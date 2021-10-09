import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;

public class crossWord{

    static Scanner inPut = new Scanner(System.in);
    static Scanner guessIn = new Scanner(System.in);
    static String result;
    static String newLine = System.getProperty("line.separator");
    static int userIn;
    static String guess;
    static int counter = 0;


//status values

    static int one = 0;
    static int two = 0;
    static int three = 0;
    static int four = 0;
    static int five = 0;
    static int six = 0;
    static int seven = 0;
    static int eight = 0;
    static int nine = 0;
    static int ten = 0;

    static String One;
    static String Two;
    static String Three;
    static String Four;
    static String Five;
    static String Six;
    static String Seven;
    static String Eight;
    static String Nine;
    static String Ten;

//word states

    static int downa = 0;
    static int downb = 0;
    static int acrossa = 0;
    static int acrossb = 0;

    //Hash to hold the state values
    static HashMap<String, String> stateValues = new HashMap<String, String>();

    static String clues(){

        return
                "Down A - A country on an island in south asia "
                        .concat(newLine)
                        .concat("Down C - Plural of something you use to water a garden")
                        .concat(newLine)
                        .concat("Across 3 - A thorny flower")
                        .concat(newLine)
                        .concat("Across 9 - The male ruler");

    }

    static String blankCon() {
        return

                "  abcd".concat(newLine).concat(One).concat(newLine).concat(Two).concat(newLine).concat(Three)
                        .concat(newLine).concat(Four).concat(newLine).concat(Five).concat(newLine).concat(Six)
                        .concat(newLine).concat(Seven).concat(newLine).concat(Eight).concat(newLine).concat(Nine)
                        .concat(newLine).concat(Ten);

    }

    public static void main(String[] args) throws InterruptedException {

//populate the HashMap
        fillTable();

        System.out.println("Do you want to try a crossword (Y/N)");
        result = inPut.nextLine();
        System.out.println(result);

        if(result.equals("Y")){

            playGame();

        }

//test fuction - not used in game play
        else if(result.equals("T")){
            System.out.println(blankCon());


        }

        else if(result.equals("N")){
            endCode();
        }

        else if(result != "Y" && (result !=  "N") && (result !="T")) {
            System.out.println("please enter either Y or N");

            //return back to the start
            main(args);
        }



        else{

            endCode();


        }


    }

    public static void playGame() throws InterruptedException {
        System.out.println("Rules: # represents empty spaces, - Represents spaces in vertical words, _ Represents where a letter goes.");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("To enter a word, first enter V for vertical or H for horizontal, and then either the row or column marker (EX. a OR 4), be careful, it is case sensitive. Then type what you think the word is, including - marks for spaces.");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("Here is the board:");
        boardLogic();
        System.out.println(blankCon());
        TimeUnit.SECONDS.sleep(4);
        System.out.println("Are you ready to see the clues and start the game?(Y/N)");

        result = inPut.nextLine();
        if(result.equals("Y")){
            System.out.println(clues());
            TimeUnit.SECONDS.sleep(3);
            chooseDirection();
        }
        else{
            main(null);
        }

        TimeUnit.SECONDS.sleep(3);

    }

    public static void chooseDirection() throws InterruptedException{

        //I created this method because I want to be able to constanly return
        //to this option section.
        if(counter < 4){

            System.out.println("(V/H)?");

            result = inPut.nextLine();
            if(result.equals("V")){
                scoreKeepVertical();
            }
            else if(result.equals("H")){
                scoreKeepHorizontal();
            }
            else{
                System.out.println("You did not enter a valid option.");
                TimeUnit.SECONDS.sleep(3);
                chooseDirection();
            }
        }
        else{
            System.out.println("You have finished the game!");
            TimeUnit.SECONDS.sleep(2);
            main(null);
        }

    }

    public static void scoreKeepHorizontal() throws InterruptedException{

        System.out.println("Would you like 3 or 9 across (3/9)");
        userIn = inPut.nextInt();

        if (userIn == 3){
            acrossThree();
        }
        else if (userIn == 9){
            System.out.println("Input what you think the word for 9 across is, not case senisitve, 4 max characters");
            guess = guessIn.nextLine();


            if(guess.toLowerCase().equals("king")){
                System.out.println("You guessed the correct answer!");
                nine = nine + 2;
                counter = counter + 1;
                acrossb = acrossb + 1;
                fillTable();
                boardLogic();
                System.out.println(blankCon());
                chooseDirection();
            }

        }
        else{
            System.out.println("Did not insert a valid horizontal row.");
            TimeUnit.SECONDS.sleep(2);
            chooseDirection();
        }

    }

    public static void acrossThree() throws InterruptedException {

        System.out.println("Input what you think the word for 3 across is, not case sensitive. 4 max characters");
        guess = guessIn.nextLine();
        guess = guess.toLowerCase();
        if(guess.equals("rose")){
            System.out.println("You guessed the answer correct.");
            three = 4;
            counter = counter + 1;
            acrossa = acrossa + 1;
            fillTable();
            boardLogic();
            System.out.println(blankCon());
            chooseDirection();
        }
        else if(guess.equals("Rose")){
            System.out.println("You guessed the answer correct.");
            three = 4;
            counter = counter + 1;
            fillTable();
            boardLogic();
            System.out.println(blankCon());
            chooseDirection();

        }
        else{
            System.out.println("Sorry that guess was incorrect.");
            TimeUnit.SECONDS.sleep(2);
            chooseDirection();
        }




    }

    public static void scoreKeepVertical()throws InterruptedException{
        System.out.println("Select a vertical word, letters are case sensitive. (a/c)");
        result = inPut.nextLine();

        if(result.equals("a")){
            System.out.println("Enter your guess for A down including the - for the space, not case sensitive.");
            guess = guessIn.nextLine();
            guess = guess.toLowerCase();

            if(guess.equals("sri-lanka")){
                System.out.println("You guessed the word correct.");
                two = two +1;
                three = three + 1;
                four = four + 1;
                six = six + 1;
                seven = seven + 1;
                eight = eight + 1;
                nine = nine + 1;
                ten = ten + 1;
                counter = counter + 1;
                boardLogic();
                System.out.println(blankCon());
                chooseDirection();
            }
            else{
                System.out.println("You guessed the word incorrect");
                TimeUnit.SECONDS.sleep(2);
                chooseDirection();

            }

        }
        else if(result.equals("c")){
            guess = guessIn.nextLine();
            guess = guess.toLowerCase();

            if(guess.equals("hoses")){
                System.out.println("You guessed the word correct");
                one = one + 1;
                two = two + 2;
                three = three + 2;
                four = four + 2;
                five = five + 1;
                counter = counter + 1;
                boardLogic();
                System.out.println(blankCon());
                chooseDirection();
            }
            else{
                System.out.println("You guessed the word incorrectly");
                TimeUnit.SECONDS.sleep(2);
                chooseDirection();
            }

        }
        else{
            System.out.println("You did not enter a valid letter.");
            TimeUnit.SECONDS.sleep(2);
            chooseDirection();
        }


    }

    public static void endCode() throws InterruptedException {

        System.out.println("Do you want to end the game (Y/N)");

        result = inPut.nextLine();

        if (result.equals("Y")){

            System.exit(0);
        }

        else{
            main(null);
        }



    }

    public static void fillTable(){
        stateValues.put("1t", "1 ##H#");
        stateValues.put("1f", "1 ##_#");
        stateValues.put("2f", "2 _#_#");
        stateValues.put("2at", "2 S#_#");
        stateValues.put("2ct", "2 _#O#");
        stateValues.put("2act", "2 S#O#");
        stateValues.put("3f", "3 ____");
        stateValues.put("3t", "3 ROSE");
        //stateValues.put("3f", "3 ####");
        stateValues.put("3ct", "3 __S_");
        stateValues.put("3at", "3 R___");
        stateValues.put("3act", "3 R_S_");
        stateValues.put("4at", "4 I#_#");
        stateValues.put("4ct", "4 _#E#");
        stateValues.put("4act", "4 I#E#");
        stateValues.put("4f", "4 _#_#");
        stateValues.put("5t", "5 -#S#");
        stateValues.put("5f", "5 -#_#");
    /*stateValues.put("6at", "6 L#_#");
    stateValues.put("6ct", "6 _#S#"); */
        stateValues.put("6t", "6 L###");
        stateValues.put("6f", "6 _###");
        stateValues.put("7t", "7 A###");
        stateValues.put("7f", "7 _###");
        stateValues.put("8t", "8 N###");
        stateValues.put("8f", "8 _###");
        stateValues.put("9t", "9 KING");
        stateValues.put("9f", "9 ____");
        stateValues.put("9at", "9 K___");
        stateValues.put("10t", "10A###");
        stateValues.put("10f", "10_###");

    }

    public static void boardLogic(){

        if(three > 4){
            three = 4;
        }
        if(nine > 2){
            nine = 2;
        }

        if(one == 0){
            One = stateValues.get("1f");
        }
        if(one == 1){
            One = stateValues.get("1t");
        }
        if(two == 0){
            Two = stateValues.get("2f");
        }
        if(two ==1){
            Two = stateValues.get("2at");
        }
        if(two == 2){
            Two = stateValues.get("2ct");
        }
        if(two == 3){
            Two = stateValues.get("2act");
        }
        if(three == 0){
            Three = stateValues.get("3f");
        }
        if(three == 1){
            Three = stateValues.get("3at");
        }
        if(three == 2){
            Three = stateValues.get("3ct");
        }
        if(three == 3){
            Three = stateValues.get("3act");
        }
        if(three == 4){
            Three = stateValues.get("3t");
        }

        if(four == 0){
            Four = stateValues.get("4f");
        }
        if(four == 1){
            Four = stateValues.get("4at");
        }
        if(four ==2){
            Four = stateValues.get("4ct");
        }
        if(four == 3){
            Four = stateValues.get("4act");
        }
        if(five == 0){
            Five = stateValues.get("5f");
        }
        if(five == 1){
            Five = stateValues.get("5t");
        }
        if(six == 0){
            Six = stateValues.get("6f");
        }
        if(six == 1){
            Six = stateValues.get("6t");
        }
    /* if(six == 2){
        Six = stateValues.get("6ct");
    }
    if(six == 3){
        Six = stateValues.get("6act");
    } */
        //my mistake, lines not needed
        if(seven == 0){
            Seven = stateValues.get("7f");
        }
        if(seven == 1){
            Seven = stateValues.get("7t");
        }
        if(eight == 0){
            Eight = stateValues.get("8f");
        }
        if(eight == 1){
            Eight = stateValues.get("8t");
        }
        if(nine == 0){
            Nine = stateValues.get("9f");
        }
        if(nine == 1){
            Nine = stateValues.get("9at");
        }
        if(nine == 2){
            Nine = stateValues.get("9t");
        }
        if(ten == 0){
            Ten = stateValues.get("10f");
        }
        if(ten == 1){
            Ten = stateValues.get("10t");
        }


    }






}


/*Correct State:

  abcd
1 ##_#
2 _#_#
3 ____
4 _#_#
5 -#_#
6 _###
7 _###
8 _###
9 ____
10_###

*/
