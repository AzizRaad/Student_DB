package advancedprogrammingproject;

import java.io.IOException;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Abdulaziz Alamoudi - Abdulaziz Bahamid
 */
public class AdvancedProgrammingProject {

    public static void main(String[] args) throws SQLException, IOException {
        // Welcoming message to program
        System.out.println("\t\t\t\t{ Welcome to Students DBMS }");
        System.out.println("\t\t\t\t\tProject Done By:");
        System.out.println("\t\t\t\t--Abdulaziz Alamoudi 441016500");
        System.out.println("\t\t\t\t--Abdulaziz Bahamid  441016576");
        Scanner input = new Scanner(System.in);
        String choice;
        
        do { // entering the menu loop till the user choses the number 4
            printMenu();
            choice = input.nextLine();
            try{
            switch (choice) { 
                case "1":
                    String name = DBUtility.readName("Enter full name ---> ");
                    String date = DBUtility.readDate();
                    double GPA = DBUtility.readGPA();
                    DBUtility.addRecord(name, date, GPA);
                    break;
                    
                case "2":
                    DBUtility.readAllRecords();
                    break;
                    
                case "3":
                    String fullname = DBUtility.readName("Enter a name to search ---> ");
                    DBUtility.searchByName(fullname);
                    break;
                    
                case "4":
                    System.out.println(" -- Thanks for using our Students DBMS -- ");
                    break;
                    
                default:
                    System.out.println("Sorry Wrong input !!");
                    System.out.println("Try Again");
                    }
            } catch(Exception Error){
                    System.out.println("Something went wrong please try again!!");
                    System.out.println("the ERROR was: " + Error);
            }// end of swtich case
        } while (!choice.equals("4"));
    }// end of main method

    public static void printMenu() {
        System.out.println("\n");
        System.out.println("Please choose from the menu below:");
        System.out.println("1. add a student");
        System.out.println("2. Read All Student Records");
        System.out.println("3. Search For a Student By Name");
        System.out.println("4. Exit");
        System.out.print("Your Option: ");
    }// end of printMenu Method
}//end of class
