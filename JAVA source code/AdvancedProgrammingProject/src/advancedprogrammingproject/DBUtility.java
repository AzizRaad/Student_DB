/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedprogrammingproject;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Aziz
 */
public class DBUtility {

    static Scanner inp = new Scanner(System.in);

    public static void addRecord(String name, String date, double GPA) throws SQLException {
        // cunstructing the query 
        String insertquery = "insert into StudentsTBL_abdulaziz_abdulaziz(FullName,DateOfBirth,GPA) values(?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(insertquery);
        statement.setString(1, name);
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setDouble(3, GPA);
        statement.execute();
        System.out.println("\t++info added successfully++\n");
        statement.close();
    }// end of add record method

    public static void readAllRecords() throws SQLException {
        String selectquery = "select * from StudentsTBL_abdulaziz_abdulaziz";
        PreparedStatement statement = getConnection().prepareStatement(selectquery);

        ResultSet printResult = statement.executeQuery();

        while (printResult.next()) {
            System.out.println(printResult.getInt(1));
            System.out.println(printResult.getString(2));
            System.out.println(printResult.getDate(3));
            System.out.println(printResult.getDouble(4));
        }// end while
        statement.close();
    }// end of readAllRecodrds method

    public static void searchByName(String fullname) throws SQLException {
        // cunstructing the query
        String searchquery = "select * from StudentsTBL_abdulaziz_abdulaziz where fullName = ? ";
        PreparedStatement statement = getConnection().prepareStatement(searchquery);
        statement.setString(1, fullname);
        ResultSet searchResult = statement.executeQuery();

        // handling not found record exception
        if (searchResult.isBeforeFirst()) {
            System.out.printf("%-5s %-15s %-15s %-5s %n", "ID", "FullName", "BirthOfDate", "GPA");
            System.out.println("------------------------------------------------------------");
            while (searchResult.next()) {
                System.out.printf("%-5s %-15s %-15s %-5s %n",
                        searchResult.getInt(1), searchResult.getString(2), searchResult.getDate(3), searchResult.getDouble(4));
            }
            System.out.println("------------------------------------------------------------");
        } else {
            System.out.println("Record not found for " + fullname);
        }
        statement.close();
    }// end of searchByName Method

    public static Connection getConnection() throws SQLException {
        String mysqlUrl = "jdbc:mysql://localhost/StudentsDB_Bahamid_AlAmoudi";
        return DriverManager.getConnection(mysqlUrl, "root", "aziz099");
    }// end of getConnection method

    public static String checkDate() {
        System.out.println("Enter birth date in yyyy-mm-dd form:");
        String date = inp.next();
//        while (!DBUtility.checkDate(date)) {
//        }
        String DATE_PATTERN = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

//        while (!date.matches(DATE_PATTERN)) {
//            System.out.println("Wrong entry , Enter the right form again");
//            System.out.println("Enter birth date in yyyy-mm-dd form:");
//            date = inp.next();
//        }
        if (!date.matches(DATE_PATTERN)) {
            checkDate();
        }

        return date;
    }// end of checkDate method

    public static String checkName() {
        String Name_Pattern = "[A-Za-z ]{1,40}";
        System.out.println("Enter Full Name:");
        String name = inp.nextLine();
        if (!name.matches(Name_Pattern)) {
            if (name.length() > 40 || name.length() < 3) {
                System.out.println("your name should be 3 to 40 characters");
            } else {
                System.out.println("please just enter characters");
            }
            checkName();
        }
        return name;
    }// end of checkName method

    public static void checkGPA() {
        System.out.println("Enter student GPA \"must be between 0-4\"");
        String GPA_Pattern = "[4]|([0-3])+[.]?[0-9]*";
    }
}// end of DBUtility method
