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
        System.out.println("\t\t{ Welcome to Students DBMS }");
        System.out.println("\t\t\tProject Done By:");
        System.out.println("\t\t--Abdulaziz Alamoudi 441016500");
        System.out.println("\t\t--Abdulaziz Bahamid  441016576");
        Scanner input = new Scanner(System.in);
        String choice;
        
        do { // entering the menu loop till the user choses the number 4
            printMenu();
            choice = input.nextLine();
            try{
            switch (choice) { 
                case "1":
                    String name = DBUtility.readName("\t\tEnter full name ---> ");
                    String date = DBUtility.readDate();
                    String GPA = DBUtility.readGPA();
                    DBUtility.addRecord(name, date, GPA);
                    break;
                    
                case "2":
                    DBUtility.readAllRecords();
                    break;
                    
                case "3":
                    String fullname = DBUtility.readName("\t\tEnter a name to search ---> ");
                    DBUtility.searchByName(fullname);
                    break;
                    
                case "4":
                    System.out.println("\t\t -- Thanks for using our Students DBMS -- ");
                    break;
                    
                default:
                    System.out.println("\t\tSorry Wrong input !!");
                    System.out.println("\t\tTry Again");
                    }
            } catch(Exception Error){
                    System.out.println("\t\tSomething went wrong please try again!!");
                    System.out.println("\t\tthe ERROR was: " + Error);
            }// end of swtich case
        } while (!choice.equals("4"));
    }// end of main method

    public static void printMenu() {
        System.out.println("\n");
        System.out.println("\t\tPlease choose from the menu below:");
        System.out.println("\t\t1. add a student");
        System.out.println("\t\t2. Read All Student Records");
        System.out.println("\t\t3. Search For a Student By Name");
        System.out.println("\t\t4. Exit");
        System.out.print("\t\tYour Option: ");
    }// end of printMenu Method
}//end of class
