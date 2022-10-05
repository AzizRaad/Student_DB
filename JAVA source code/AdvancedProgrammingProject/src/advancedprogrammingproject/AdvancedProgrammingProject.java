package advancedprogrammingproject;

import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Abdulaziz Alamoudi - Abdulaziz Bahamid
 */
public class AdvancedProgrammingProject {

    public static void main(String[] args) throws SQLException {
//        Driver md = new com.mysql.jdbc.Driver();
//        DriverManager.registerDriver(md);

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
                    System.out.println("Enter student GPA \"must be between 0-4\"");
                    double GPA = input.nextDouble();
                    addRecord(name, date, GPA);
                    break;
                case '2':
                    readAllRecords();
                    break;
                case '3':
                    System.out.println("Enter student name:");
                    String fullname = input.next();
                    searchByName(fullname);
                    break;
                case '4':
                    System.out.println(" -- Thanks for using our Students DBMS -- ");
                    break;
                default:
                    System.out.println("Sorry Wrong input !!");
                    System.out.println("Try Again");
            }
        } while (choice != '4');

    }// end of main method

    public static void printMenu() {
        System.out.println("\tWelcome to Students DBMS, Choose an option: ");
        System.out.println("1. add a student");
        System.out.println("2. Read All Student Records");
        System.out.println("3. Search For a Student By Name");
        System.out.println("4. Exit");
    }// end of printMEnu Method

    public static void addRecord(String name, String date, double GPA) throws SQLException {
        // creating connection to the data bsae
        String mysqlUrl = "jdbc:mysql://localhost/StudentsDB_Bahamid_AlAmoudi";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "aziz099");
        // cunstructing the query 
        String insertquery = "insert into StudentsTBL_abdulaziz_abdulaziz(FullName,DateOfBirth,GPA) values(?,?,?)";
        PreparedStatement statement = con.prepareStatement(insertquery);
        statement.setString(1, name);
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setDouble(3, GPA);
        statement.execute();
        System.out.println("\t++info added successfully++\n");
    }// end of add record method

    public static void readAllRecords() throws SQLException {
        String mysqlUrl = "jdbc:mysql://localhost/StudentsDB_Bahamid_AlAmoudi";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "aziz099");

        String selectquery = "select * from StudentsTBL_abdulaziz_abdulaziz";
        PreparedStatement statement = con.prepareStatement(selectquery);

        ResultSet printResult = statement.executeQuery();

        while (printResult.next()) {
            System.out.println(printResult.getInt(1));
            System.out.println(printResult.getString(2));
            System.out.println(printResult.getDate(3));
            System.out.println(printResult.getDouble(4));
        }// end while
    }// end of readAllRecodrds method

    public static void searchByName(String fullname) throws SQLException {
        // creating connection to the data base
        String mysqlUrl = "jdbc:mysql://localhost/StudentsDB_Bahamid_AlAmoudi";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "aziz099");
        // cunstructing the query
        String searchquery = "select * from StudentsTBL_abdulaziz_abdulaziz where fullName = ?";
        PreparedStatement statement = con.prepareStatement(searchquery);
        statement.setString(1, fullname);
        ResultSet searchResult = statement.executeQuery();
        while (searchResult.next()) {
            System.out.println(searchResult.getInt(1));
            System.out.println(searchResult.getString(2));
            System.out.println(searchResult.getDate(3));
            System.out.println(searchResult.getDouble(4));
        }
    }// end of searchByName Method

}//end of class
