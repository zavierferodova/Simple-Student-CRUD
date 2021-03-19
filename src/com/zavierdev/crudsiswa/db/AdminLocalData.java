/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.db;

import com.zavierdev.crudsiswa.model.Admin;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tech
 */
public class AdminLocalData extends LocalData {
    private static AdminLocalData instance = null;
    private DatabaseHelper dbHelper;
    private final String tableName = "admins";
    
    public static AdminLocalData getInstance(DatabaseHelper databaseHelper) {
        if(instance == null) {
            synchronized(AdminLocalData.class) {
                instance = new AdminLocalData();
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
    
    public ResultSet getAdmin(Admin admin) {
        String preparedQuery = "";		
        List<String> queryValue = new ArrayList();
        
        if(admin.getId() != null) {
            if(!admin.getId().isEmpty()) {
                preparedQuery = "SELECT * FROM " + tableName + " WHERE id=?";   
                queryValue.add(admin.getId());
            }
        }            
        else {
            preparedQuery = "SELECT * FROM " + tableName + " WHERE username=?";
            queryValue.add(admin.getUsername());
        }
            
        return dbHelper.getPreparedQueryResult(preparedQuery, queryValue.toArray(String[]::new));
    }
    
    public void updateAdmin(Admin admin) {
        String preparedQuery = "UPDATE "
                + tableName 
                +" SET username=?, password=?, salt=?, last_login=NULL WHERE id=?";
        String queryValue[] = {
            admin.getUsername(), admin.getPassword(), admin.getSalt(), admin.getId()
        };
        
        dbHelper.executeUpdate(preparedQuery, queryValue);
    }
}
