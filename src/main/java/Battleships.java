import java.util.*;

public class Battleships {
    public static void main(String[] args){
        printBattleField();
    }

    public static void printMap(int[][] map){
        System.out.println("  0123456789  ");
        for(int row = 0; row < map.length; row++){
            System.out.print(row + "|");
            for(int col = 0; col < map[row].length; col++){
                if (map[row][col] == 1) {
                    System.out.print("@");
                }
                else if (map[row][col] == 2) {
                    System.out.print("#");
                }
                else if (map[row][col] == 11 || map[row][col] == 21) {
                    System.out.print("x");
                }
                else if (map[row][col] == 12 || map[row][col] == 22) {
                    System.out.print("!");
                }
                else if (map[row][col] == 14) {
                    System.out.print("-");
                }
                else {System.out.print(".");}
            }
            System.out.print("|" + row);
            System.out.println();
        }
        System.out.println("  0123456789  ");
    }


    public static void printBattleField(){

        System.out.println("**** Welcome to Battle Ships game ****");

        int[][] myMap = new int[10][10];
        int[][] computerMap = new int[10][10];

        printMap(myMap);

        Scanner input = new Scanner(System.in);
        System.out.println("Please deploy your ship on the map by providing their indexes -[][]-");
        for(int i = 0; i < 5; i++) {
            System.out.print("Enter 'X' coordinates for your ship: ");
            int x = input.nextInt();
            System.out.print("Enter 'Y' coordinates for your ship: ");
            int y = input.nextInt();

            if(x > 9 || y > 9) {
                System.out.println("Please provide correct index in range from 0 to 9");
            }
            if(myMap[x][y] == 1){
                System.out.println("You already have a ship here");
            }
            else {myMap[x][y] = 1;}
        }

        printMap(myMap);

        System.out.println("The computer will deploy its ships now");
        int i = 0;
        while (i < 5) {
            Random r = new Random();
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            if(computerMap[x][y] == 2){
                System.out.println("You already have the ship here");
            }
            else {
                computerMap[x][y] = 2;
                i++;
                System.out.println("Ship " + i + " deployed");
            }
        }

        int player_ships = 5;
        int computer_ships = 5;

        while(player_ships > 0 || computer_ships > 0){

            System.out.println("YOUR TURN");
            System.out.print("Enter X coordinates");
            int user_x = input.nextInt();
            System.out.print("Enter Y coordinates");
            int user_y = input.nextInt();

            if(myMap[user_x][user_y] == 1) {
                System.out.println("Oh no, you sunk your own ship :(");
                myMap[user_x][user_y] = 11;
                player_ships --;
                System.out.println("ships left: " + player_ships);
            }
            else if(computerMap[user_x][user_y] == 2){
                System.out.println("Boom! You sunk the ship!");
                computerMap[user_x][user_y] = 12;
                myMap[user_x][user_y] = 12;
                computer_ships --;
            }
            else {
                System.out.println("Sorry, you missed");
                myMap[user_x][user_y] = 14;
            }

//            System.out.println("#######" + myMap[user_x][user_y]);

            System.out.println("COMPUTER'S TURN");
            Random com_r = new Random();
            int com_x = com_r.nextInt(10);
            int com_y = com_r.nextInt(10);

            if (myMap[com_x][com_y] == 1){
                System.out.println("The Computer sunk one of your ships!");
                computerMap[com_x][com_y] = 21;
                myMap[com_x][com_y] = 21;
                player_ships --;
            }
            else if (computerMap[com_x][com_y] == 2) {
                System.out.println("The Computer sunk one of its own ships");
                myMap[com_x][com_y] = 22;
                computerMap[com_x][com_y] = 22;
                computer_ships --;
            }
            else {
                System.out.println("Computer missed");
                computerMap[com_x][com_y] = 24;
            }

            printMap(myMap);
            if (player_ships ==0) {
                System.out.println("Player ships: " + player_ships + " Computer ships: " + computer_ships);
                System.out.println("You lost all the ships");
                System.out.println("--- G A M E   O V E R ---");
                break;
            }
            else if (computer_ships == 0) {
                System.out.println("Player ships: " + player_ships + " Computer ships: " + computer_ships);
                System.out.println("CONGRATULATIONS, you won!!!");
                break;
            }
        }

    }
}
