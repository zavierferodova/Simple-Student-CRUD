/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.di;

import com.zavierdev.crudsiswa.data.AdminRepository;
import com.zavierdev.crudsiswa.data.StudentRepository;
import com.zavierdev.crudsiswa.db.AdminLocalData;
import java.sql.Connection;
import com.zavierdev.crudsiswa.db.DatabaseConnection;
import com.zavierdev.crudsiswa.db.DatabaseHelper;
import com.zavierdev.crudsiswa.db.StudentLocalData;

/**
 *
 * @author tech
 */
public class RepositoryInjection {
    public static StudentRepository getStudentRepository() {
        Connection connection = DatabaseConnection.getInstance().get(); //Open DB Connection
        DatabaseHelper dbHelper = new DatabaseHelper(connection);
        StudentLocalData localData = StudentLocalData.getInstance(dbHelper);
        return StudentRepository.getInstance(localData);
    }
    
    public static AdminRepository getAdminRepository() {
        Connection connection = DatabaseConnection.getInstance().get(); //Open DB Connection
        DatabaseHelper dbHelper = new DatabaseHelper(connection);
        AdminLocalData localData = AdminLocalData.getInstance(dbHelper);
        return AdminRepository.getInstance(localData);
    }
}
