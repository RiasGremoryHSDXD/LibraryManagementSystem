// This file used to reference about retrieving data from Database

import java.sql.*;

public class Student {
    public static void main(String[] args) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_connect_db", "root", "");
            Statement display_all_name = connection.createStatement();
            ResultSet resultSet = display_all_name.executeQuery("SELECT * FROM student");

            int count_record = 1;
            while (resultSet.next()) {
                String student_full_name = resultSet.getString("student_name");
                int student_age = resultSet.getInt("student_age");
                System.out.println("student name : " + student_full_name);
                System.out.println("student age: " + student_age);
                count_record++;
            }
        } catch (Exception e) {
            System.out.println("Error in Seeing Class Record");
            e.printStackTrace();
        }
    }
}
