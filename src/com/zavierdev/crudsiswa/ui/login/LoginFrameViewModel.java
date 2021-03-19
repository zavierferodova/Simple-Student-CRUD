/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.ui.login;

import com.zavierdev.crudsiswa.auth.Session;
import com.zavierdev.crudsiswa.viewmodel.ViewModelConnectionObserver;
import com.zavierdev.crudsiswa.data.AdminRepository;
import com.zavierdev.crudsiswa.data.Repository;
import com.zavierdev.crudsiswa.di.RepositoryInjection;
import com.zavierdev.crudsiswa.model.Admin;
import com.zavierdev.crudsiswa.utils.PasswordUtils;

/**
 *
 * @author tech
 */
public final class LoginFrameViewModel {
    private AdminRepository repository = null;
    private ViewModelConnectionObserver connectionObserver;
    
    public LoginFrameViewModel() {
        super();
        connectDB();
    }
    
    public void connectDB() {
        repository = RepositoryInjection.getAdminRepository();
    }
    
    private void startObserveConnection() {
        repository.observeConnectionDB(new Repository.ConnectionObserver() {
            @Override
            public void connectionStatus(boolean status) {
                connectionObserver.connectionStatus(status);
            }
        });
    }
    
    public void observeConnection(ViewModelConnectionObserver connectionObserver) {
        this.connectionObserver = connectionObserver;
        startObserveConnection();
    }
    
    public Session performLogin(String username, String password) {
        boolean sucessLogin = false;
        Session session = new Session();
        Admin adminFromUser = new Admin();
        
        session.setStatusLogin(sucessLogin);
        adminFromUser.setUsername(username);
        adminFromUser.setPassword(password);
        
        Admin adminFromDB = repository.getAdmin(adminFromUser);
        if(adminFromDB != null) {
            sucessLogin = loginValidation(adminFromUser, adminFromDB);
        
            if(sucessLogin) {
                repository.updateAdmin(adminFromDB);        
                session.setId(adminFromUser.getId());
                session.setUsername(adminFromUser.getUsername());
                session.setStatusLogin(sucessLogin);
            }
        }
        
        return session;
    }
    
    private boolean loginValidation(Admin adminFromUser, Admin adminFromDB) {
        boolean isSucess = false;        
        String typedUsername;
        String typedPassword;
        String dbUsername;
        String dbPassword;
        String dbSalt;
               
        typedUsername = adminFromUser.getUsername();
        typedPassword = adminFromUser.getPassword();
        dbUsername = adminFromDB.getUsername();
        dbPassword = adminFromDB.getPassword();
        dbSalt = adminFromDB.getSalt();
            
        boolean isUsernameCorrect = dbUsername.equals(typedUsername);
        boolean isPasswordCorrect = PasswordUtils.verifyUserPassword(
                    typedPassword, dbPassword, dbSalt);
            
        if(isUsernameCorrect && isPasswordCorrect) {
            isSucess = true;
        }
        
        return isSucess;
    }
}
