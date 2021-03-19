/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.db;

import com.zavierdev.crudsiswa.model.Student;
import com.zavierdev.crudsiswa.utils.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tech
 */
public class StudentLocalData extends LocalData {
    private static StudentLocalData instance = null;
    private DatabaseHelper dbHelper;
    private final String tableName = "students";
    
    public static StudentLocalData getInstance(DatabaseHelper databaseHelper) {
        if(instance == null) {
            synchronized(StudentLocalData.class) {
                instance = new StudentLocalData();                
            }
        }
        
        if(instance != null) {
            instance.dbHelper = databaseHelper;
        }
        
        return instance;
    }
    
    
    @Override
    public boolean checkConnection() {
        boolean isConnected = false;
        
        try {
            String query = "SELECT * FROM "+ tableName +" LIMIT 1";
            ResultSet result = dbHelper.getResultSet(query);
            
            if(result != null) {
                isConnected = true;
            }
        } catch (Exception e) { }
        
        return isConnected;
    }
    
    public String generateId(int length) {
        String id = "";
        ResultSet studentResult;
        
        try {
            do {
                id = StringUtils.randomString(20);
                Student student = new Student();
                student.id = id;
                studentResult = getStudent(student);
            } while(studentResult.next());
        } catch(SQLException e) {
            System.err.println("Failed to generate student Id");
        }
                
        return id;
    }
    
    public ResultSet getStudents() {
        String query = "SELECT * FROM " + tableName +" ORDER BY updated_at DESC, created_at DESC";
        return dbHelper.getResultSet(query);
    }
    
    public ResultSet getStudent(Student student) {
        String preparedQuery = "SELECT * FROM " + tableName + " WHERE id=?";
        String queryValue[] = { student.id };
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue);
    }
    
    public ResultSet searchStudents(String keyword) {
        String preparedQuery = "SELECT * FROM " + tableName + " WHERE (id LIKE ? OR name LIKE ?)";
        String queryValue[] = {
            "%"+keyword+"%", "%"+keyword+"%"
        };
        
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue);
    }
    
    public void addStudent(Student student) {
        String preparedQuery = "INSERT INTO "+ tableName +" VALUES(?, ?, ?, NULL, NULL)";
        String studentInfo[] = {
            generateId(20), student.name, student.address
        };

        dbHelper.executeUpdate(preparedQuery, studentInfo);
    }
    
    public void updateStudent(Student student) {
        String preparedQuery = "UPDATE "+ tableName +" SET name=?, address=?, updated_at=NULL WHERE id=?";
        String queryValue[] = {
            student.name, student.address, student.id
        };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
    
    public void deleteStudent(Student student) {
        String preparedQuery = "DELETE FROM "+ tableName +" WHERE id=?";
        String queryValue[] = { student.id };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
}
