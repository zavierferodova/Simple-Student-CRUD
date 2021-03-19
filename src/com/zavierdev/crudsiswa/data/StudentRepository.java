/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.data;

import com.zavierdev.crudsiswa.db.StudentLocalData;
import com.zavierdev.crudsiswa.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tech
 */
public final class StudentRepository extends Repository {
    private static StudentRepository instance = null;
    private StudentLocalData localData = null;
    
    public StudentRepository() {
        super();
    }
    
    public static StudentRepository getInstance(StudentLocalData localData) {
        if(instance == null) {
            synchronized(StudentLocalData.class) {
                instance = new StudentRepository();
            }
        }
        
        if(instance != null) {
            instance.localData = localData;
            instance.setLocalData(localData);
            instance.checkConnection();
        }
        
        return instance;
    }
    
    private boolean checkConnectionDB() {
        boolean status = super.checkConnection();
        return status;
    }
    
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList();
        ResultSet results = localData.getStudents();
        
        try {
            if(results != null) {
                while(results.next()) {
                    Student student = new Student();
                    student.setId(results.getString("id"));
                    student.setName(results.getString("name"));
                    student.setAddress(results.getString("address"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to get students");
        }
        
        return students;
    }
    
    public ArrayList<Student> searchStudents(String keyword) {
        checkConnectionDB();
        ArrayList<Student> students = new ArrayList();
        ResultSet results = localData.searchStudents(keyword);
        
        try {
            if(results != null) {
                while(results.next()) {
                    Student student = new Student();
                    student.setId(results.getString("id"));
                    student.setName(results.getString("name"));
                    student.setAddress(results.getString("address"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to search students");
        }
        
        return students;
    }
    
    public void addStudent(Student student) {
        checkConnectionDB();
        localData.addStudent(student);
    }
    
    public void updateStudent(Student student) {
        checkConnectionDB();
        localData.updateStudent(student);
    }
    
    public void deleteStudent(Student student) {
        checkConnectionDB();
        localData.deleteStudent(student);
    }   
}
