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

        // THIS PART RETRIEVES DATA FROM DBMS-----------------------------------------
        String mysqlUrl = "jdbc:mysql://bdwyaxuoiy38wddwtdxs-mysql.services.clever-cloud.com/bdwyaxuoiy38wddwtdxs";
        Connection con = DriverManager.getConnection(mysqlUrl, "uewldxfzpbauu3nf", "vTJ5cQWE1Wv9aRo0z0ZT");
        System.out.println("Connection established: " + con);

        String selectqu = "select * from WORD";
        PreparedStatement sst = con.prepareStatement(selectqu);

        ResultSet rst = sst.executeQuery();

        while (rst.next()) {
            System.out.println(rst.getInt(1));
            System.out.println(rst.getString(2));
            System.out.println(rst.getString(3));
//            System.out.println(rst.getDate(3));
//            System.out.println(rst.getDouble(4));
        }
        //THIS PART INSERTS DATA INTO TABLE -------------------------------
//        String insertqu = "insert into StudentsTBL_abdulaziz_abdulaziz(FullName,DateOfBirth,GPA) values(?,?,?)";
//        PreparedStatement ist = con.prepareStatement(insertqu);
//        ist.setString(1, "ahmed");
//        ist.setDate(2, java.sql.Date.valueOf("2020-09-04"));
//        ist.setDouble(3, 3.4);
//        ist.execute();
        //THIS PART SEARCHES FOR ALL MATCHING RESULTS IN DATABASE BASED ON FULLNAME-------------
//        System.out.println("------------------------------------------");
//        String searchqu = "select * from StudentsTBL_abdulaziz_abdulaziz where fullName = ?";
//        PreparedStatement qst = con.prepareStatement(searchqu);
//        qst.setString(1, "aziz");
//        ResultSet searchResult = qst.executeQuery();
//        while (searchResult.next()) {
//            System.out.println(searchResult.getInt(1));
//            System.out.println(searchResult.getString(2));
//            System.out.println(searchResult.getDate(3));
//            System.out.println(searchResult.getDouble(4));
//        }
        //--------------------------------------       
        System.out.println("Project Done By: Abdulaziz Alamoudi + Abdulaziz Bahamid");
        Scanner input = new Scanner(System.in);
        char choice;
        do {
            printMenu();
            choice = input.next().charAt(0);
            switch (choice) {
                case '1':
                    //addStudent();
                    break;
                case '2':
                    //readAllRecords();
                    break;
                case '3':
                    // searchByName();
                    break;
                case '4':
                    System.out.println(" -- Thanks for using our Students DBMS -- ");
                    break;
                default:
                    System.out.println("Sorry Wrong input !!");
                    System.out.println("Try Again");
            }
        } while (choice != '4');

    }

    public static void printMenu() {
        System.out.println("Welcome to Students DBMS, Choose an option: ");
        System.out.println("1. add a student");
        System.out.println("2. Read All Student Records");
        System.out.println("3. Search For a Student By Name");
        System.out.println("4. Exit");
    }
//    public void readAllRecords() {
//
//    }
//
//    public void addRecord(int parameterIndex, int value) {
//
//    }
}
