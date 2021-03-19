/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.data;

import com.zavierdev.crudsiswa.db.LocalData;

/**
 *
 * @author tech
 */
public class Repository {
    private LocalData localData;
    private ConnectionObserver connectionObserver = null;
   
    protected void setLocalData(LocalData localData) {
        this.localData = localData;
    }
   
    protected boolean checkConnection() {
        boolean status = localData.checkConnection();
        setConnectionStatus(status);
        return status;
    }
   
    private void setConnectionStatus(boolean status) {
        boolean isObserverAvailable = connectionObserver != null;
        
        if(isObserverAvailable) {
            connectionObserver.connectionStatus(status);
        }
    }
    
    public void observeConnectionDB(ConnectionObserver connectionObserver) {
        this.connectionObserver = connectionObserver;
        checkConnection();
    }
   
    public interface ConnectionObserver {
        void connectionStatus(boolean status);
    }
}
