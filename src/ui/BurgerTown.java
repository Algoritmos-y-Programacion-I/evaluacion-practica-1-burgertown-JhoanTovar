package ui;

import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] price;
    public static int[] units;

    public static void main(String[] args) {

        initializeGlobals();
        menu();
    }

   /**
     * Description: This method is responsible for initializing the global variables.
     * pre: The Scanner reader must be declared.
     * post: The scanner reader is initialized.
    */
    public static void initializeGlobals() {

        reader = new Scanner(System.in);

    }

     /**
     * Description: This method is responsible for displaying the menu to the user and showing the result messages for each functionality.
     * pre: The Scanner reader must be initialized.
     * pre: The price array must be initialized.
    */
    public static void menu() {

        System.out.println("Welcome to BurgerTown!");

        setQuantitySold();

        boolean out = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities of each dish sold during the day.");
            System.out.println("2. Calculate the total number of dishes sold in the day.");
            System.out.println("3. Calculate the average price of the dishes sold during the day.");
            System.out.println("4. Calculate the total sales (money collected) during the day.");
            System.out.println("5. Query the number of plates that exceeded a minimum sales limit during the day.");
            System.out.println("6. Exit");
            System.out.println("\nType the option to execute");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    requestData();
                    break;
                case 2:
                    System.out.println("\nThe total number of dishes sold during the day was: " + calculateTotalDishesSold());
                    break;
                case 3:
                    System.out.println("\nThe average price of the dishes sold during the day was: " + calculateAveragePrice());
                    break;
                case 4:
                    System.out.println("\nTotal sales (money collected) during the day were: " + calculateTotalSales());
                    break;
                case 5:
                    System.out.println("\nEnter the minimum sales limit to analyze:");
                    double limit = reader.nextDouble();
                    System.out.println("\nOut of the " + price.length + " references sold during the day, " + consultPlatesAboveLimit(limit) + " exceeded the minimum sales limit of " + limit);
                    break;
                case 6:
                    System.out.println("\nThank you for using our services!");
                    out = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nInvalid option, try again.");
                    break;
            }

        } while (!out);

    }

    /**
     * Description: This method is responsible for asking the user for the number of different dishes sold during the day and initializes with that value the arrays responsible for storing prices and quantities.
     * pre: The Scanner reader must be initialized.
     * pre: The price and units arrays must be declared.
     * post: The price and units arrays are initialized.
     */
    public static void setQuantitySold() {

        System.out.println("\nEnter the number of different dishes sold during the day:");
        int dishes = reader.nextInt();

        price = new double[dishes];
        units = new int[dishes];

    }

    /**
     * 
     */
    public static void requestData(){
        for(int i = 0; i < units.length; i ++){
            int dishId = i + 1;
            System.out.println("Please enter the price of dish " + dishId);
            double dishPrice = reader.nextDouble();
            System.out.println("Please enter the quantity of dishes sold for dish " + dishId);
            int soldDishes = reader.nextInt();

            price[i] = dishPrice;
            units[i] = soldDishes;
        }
    }

    /**
     * Description: This method calculates the total number of plates that were entered.
     * @return int; totalDishCount.
     */
    public static int calculateTotalDishesSold(){
        int totalDishCount = 0;
        for(int i = 0; i < units.length; i++){
            totalDishCount += units[i];
        }
        return totalDishCount;
    }

    /**
     * Description: This method calculates the average of the total 
     * sales made by the restaurant. 
     * It takes the total amount of money
     * collected and divides it by the total amount of dishes sold.
     * @return double; averageDishPrice.
     */
    public static double calculateAveragePrice(){
        double totalPrices = 0;
        double totalRevenue = 0;
        double averageDishPrice = 0;
        int totalSoldDishes = 0;

        for(int i = 0; i < units.length; i++){
            totalSoldDishes += units[i];
        }

        for(int i = 0; i < units.length; i++){
            totalRevenue += (units[i] * price[i]);
            totalPrices += totalRevenue;
            totalRevenue = 0;

            if(totalSoldDishes > 0 && totalPrices >= 0 ){
                averageDishPrice = totalPrices / totalSoldDishes;
            } else {
                averageDishPrice = 0;
            }
        }

        return averageDishPrice;
    }

    /**
     * 
     * @return
     */
    public static double calculateTotalSales(){
        double totalSales = 0;
        double dishRevenue = 0;
        for(int i = 0; i < units.length; i++){
            dishRevenue += (units[i] * price[i]);
            totalSales += dishRevenue;
            dishRevenue = 0;
        }

        return totalSales;
    }

    /**
     * Description: This method compares the amount of money
     *  raised by the sales of a product to see if it exceeds a 
     * threshold given by the user.
     * @param double limit
     * @return int; dishCount.
     */
    public static int consultPlatesAboveLimit(double limit){

        int dishCount = 0;

        for(int i = 0; i < price.length; i++){
            if((units[i] * price[i]) >= limit){
                dishCount++;
            }
        }
        return dishCount;
    }

}
