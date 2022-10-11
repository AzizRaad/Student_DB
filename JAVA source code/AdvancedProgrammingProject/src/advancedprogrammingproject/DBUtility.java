/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedprogrammingproject;
import java.sql.*;

/**
 *
 * @author Aziz
 */
public class DBUtility {
    
    public static void addRecord(String name, String date, double GPA) throws SQLException {
        // cunstructing the query 
        String insertquery = "insert into StudentsTBL_abdulaziz_abdulaziz(FullName,DateOfBirth,GPA) values(?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(insertquery);
        statement.setString(1, name);
        statement.setDate(2, java.sql.Date.valueOf(date));
        
//        System.out.println(java.sql.Date.valueOf(date));
        statement.setDouble(3, GPA);
        statement.execute();
        System.out.println("\t++info added successfully++\n");
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
    }// end of readAllRecodrds method
    
    public static void searchByName(String fullname) throws SQLException {
        // cunstructing the query
        String searchquery = "select * from StudentsTBL_abdulaziz_abdulaziz where fullName = ?";
        PreparedStatement statement = getConnection().prepareStatement(searchquery);
        statement.setString(1, fullname);
        ResultSet searchResult = statement.executeQuery();
        
        // handling not found record exception
        if (searchResult.isBeforeFirst()) {                 
            while (searchResult.next()) {
                System.out.println(searchResult.getInt(1));
                System.out.println(searchResult.getString(2));
                System.out.println(searchResult.getDate(3));
                System.out.println(searchResult.getDouble(4));
            }
        }else
            System.out.println("Record not found for "+fullname);
    }// end of searchByName Method
    
     public static Connection getConnection() throws SQLException {
        String mysqlUrl = "jdbc:mysql://localhost/StudentsDB_Bahamid_AlAmoudi";
        return DriverManager.getConnection(mysqlUrl, "root", "aziz099");
    }// end of getConnection method
    
     public static boolean checkDate(String input){
         if (input.matches("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}"))
             return true;
         else return false;
     }
     
}
