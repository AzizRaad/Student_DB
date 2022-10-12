/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedprogrammingproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Aziz
 */
public class DBUtility {

    static Scanner input = new Scanner(System.in);
    
    public static Connection getConnection() throws SQLException, FileNotFoundException, IOException {
        Properties config=new Properties();
        FileInputStream ip= new FileInputStream("config.properties");
        config.load(ip);
        
        return DriverManager.getConnection(config.getProperty("DBUrl"), config.getProperty("user"), config.getProperty("password"));
    }// end of getConnection method

    public static void addRecord(String name, String date, String GPA) throws SQLException, IOException {
        // cunstructing the query and connect to the database
        String insertquery = "insert into StudentsTBL_abdulaziz_abdulaziz(FullName,DateOfBirth,GPA) values(?,?,?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(insertquery);
        
        statement.setString(1, name);
        statement.setDate(2, java.sql.Date.valueOf(date));
        statement.setDouble(3, Double.parseDouble(GPA));
        statement.execute();
        System.out.println("\t++info added successfully++\n");
        
        // closing the opened connections
        statement.close();
        connection.close();
    }// end of add record method

    public static void readAllRecords() throws SQLException, IOException {
        // cunstructing the query and connect to the database
        String selectquery = "select * from StudentsTBL_abdulaziz_abdulaziz";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(selectquery);
        ResultSet printResult = statement.executeQuery();

        // printing the result
        if (printResult.isBeforeFirst()) { //checks if there is records returned
            System.out.printf("%-5s %-15s %-15s %-5s %n", "ID", "FullName", "BirthOfDate", "GPA");
            System.out.println("------------------------------------------------------------");
            while (printResult.next()) {
                System.out.printf("%-5s %-15s %-15s %-5s %n",
                        printResult.getInt(1), printResult.getString(2), printResult.getDate(3), printResult.getDouble(4));
            }
            System.out.println("------------------------------------------------------------");
        } else {//did not fount any match in the data exception
            System.out.println("There are NO records in the database !!");
        }
        
        // closing the opened connections
        statement.close();
        connection.close();
    }// end of readAllRecodrds method

    public static void searchByName(String fullname) throws SQLException, IOException {
        // cunstructing the query and connect to the database
        String searchquery = "select * from StudentsTBL_abdulaziz_abdulaziz where fullName = ? ";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(searchquery);
        statement.setString(1, fullname);
        ResultSet searchResult = statement.executeQuery();

        // handling not found records exception
        if (searchResult.isBeforeFirst()) {//checks if there is records returned
            System.out.printf("%-5s %-15s %-15s %-5s %n", "ID", "FullName", "BirthOfDate", "GPA");
            System.out.println("------------------------------------------------------------");
            while (searchResult.next()) {// printin the found records
                System.out.printf("%-5s %-15s %-15s %-5s %n",
                        searchResult.getInt(1), searchResult.getString(2), searchResult.getDate(3), searchResult.getDouble(4));
            }
            System.out.println("------------------------------------------------------------");
        } else {
            System.out.println("No Records found for the name '" + fullname + "'");
        }
        
        // closing the opened connections
        statement.close();
        connection.close();
    }// end of searchByName Method

    public static String readName() {
        System.out.print("Enter Full Name: ");
        // constructin the regular expression to check the format and valditiy of the input with it
        String Name_Pattern = "[A-Za-z ]{3,40}";
        String name = input.nextLine();
        
        if (!name.matches(Name_Pattern)) {//matching the input with the regular expression
            if (name.length() > 40 || name.length() < 3) {//checks what error the user madde
                System.out.println("your name should be 3 to 40 characters !!");
            } else {
                System.out.println("please just enter characters not numbers !!");
            }
            name = readName(); // keeps recalling the same method till receive a valid input
        }
        return name;
    }// end of checkName method

    public static String readDate() {
        System.out.print("Enter birth date in yyyy-mm-dd form: ");
        String date = input.nextLine();
        // constructin the regular expression to check the format and valditiy of the input with it
        String DATE_PATTERN = "((18|19|20)[0-9]{2}[-](0?[13578]|1[02])[-](0?[1-9]|[12][0-9]|3[01]))|"
                + "(18|19|20)[0-9]{2}[-](0?[469]|11)[-](0?[1-9]|[12][0-9]|30)|"
                + "(18|19|20)[0-9]{2}[-](0?[2])[-](0?[1-9]|1[0-9]|2[0-8])|"
                + "(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[-](0?[2])[-]29";

        if (!date.matches(DATE_PATTERN)) { //matching the input with the regular expression
            String dateFormat = "\\d{4}-(\\d{2}|\\d)-(\\d{2}|\\d)";
            if (!date.matches(dateFormat)) { //checks what error the user madde
                System.out.println("please enter date in the yyyy-mm-dd form !!");
            } else {
                System.out.println("please enter a valid date !!");
            }
            date = readDate(); // keeps recalling the same method till receive a valid input
        }

        return date;
    }// end of checkDate method

    public static String readGPA() {
        System.out.print("Enter student GPA \"must be between 0.00 to 4.00\": ");
        // constructin the regular expression to check the format and valditiy of the input with it
        String GPA_Pattern = "[4](.[0]+)?|([0-3])+([.][0-9]+)?";
        String GPA = input.nextLine();

        if (!GPA.matches(GPA_Pattern)) { //matching the input with the regular expression
            System.out.println("please enter a GPA between 0.00 to 4.00 !!");
            GPA = readGPA(); // keeps recalling the same method till receive a valid input
        }
        return GPA;
    }// end of checkGPA method
}// end of DBUtility Class
