package advancedprogrammingproject;

import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Abdulaziz Alamoudi - Abdulaziz Bahamid
 */
public class AdvancedProgrammingProject {

    public static void main(String[] args) throws SQLException {
        // Welcoming message to program
        System.out.println("\t\t---{Project Done By: Abdulaziz Alamoudi + Abdulaziz Bahamid}---\n");
        Scanner input = new Scanner(System.in);
        char choice;
        do {
            printMenu();
            choice = input.next().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("Enter Full Name:");
                    String name = input.next();
                    System.out.println("Enter birth date in yyyy-mm-dd form:");
                    String date = input.next();
                    System.out.println(java.sql.Date.valueOf(date));
                    if (date.substring(0, 4) == "0000") {
                        while (!DBUtility.checkDate(date)) {
                            System.out.println("Wrong entry , Enter the right form again");
                            System.out.println("Enter birth date in yyyy-mm-dd form:");
                            date = input.next();
                        }
                    }
                    System.out.println("Enter student GPA \"must be between 0-4\"");
                    double GPA = input.nextDouble();
//                    DBUtility.addRecord(name, date, GPA);
                    break;
                case '2':
                    DBUtility.readAllRecords();
                    break;
                case '3':
                    System.out.println("Enter student name:");
                    String fullname = input.next();
                    DBUtility.searchByName(fullname);
                    break;
                case '4':
                    System.out.println(" -- Thanks for using our Students DBMS -- ");
                    break;
                default:
                    System.out.println("Sorry Wrong input !!");
                    System.out.println("Try Again");
            }// end of swtich case
        } while (choice != '4');
    }// end of main method

    public static void printMenu() {
        System.out.println("{Welcome to Students DBMS }");
        System.out.println("1. add a student");
        System.out.println("2. Read All Student Records");
        System.out.println("3. Search For a Student By Name");
        System.out.println("4. Exit");
        System.out.println("Choose An Osption: ");
    }// end of printMEnu Method
}//end of class
