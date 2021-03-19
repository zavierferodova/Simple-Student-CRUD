/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.ui.home;

import com.zavierdev.crudsiswa.viewmodel.ViewModelConnectionObserver;
import com.zavierdev.crudsiswa.data.Repository;
import com.zavierdev.crudsiswa.data.StudentRepository;
import com.zavierdev.crudsiswa.di.RepositoryInjection;
import com.zavierdev.crudsiswa.model.Student;
import java.util.ArrayList;

/**
 *
 * @author tech
 */
public final class HomeFrameViewModel {
    private StudentRepository repository = null;
    private ViewModelConnectionObserver connectionObserver;
    
    public HomeFrameViewModel() {
        super();
        connectDB();
    }
    
    public void connectDB() {
        repository = RepositoryInjection.getStudentRepository();
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
    
    public ArrayList<Student> getStudents() {
        return repository.getStudents();
    }
    
    public ArrayList<Student> searchStudents(String keyword) {
        return repository.searchStudents(keyword);
    }
    
    public void addStudent(Student student) {
        repository.addStudent(student);
    }
    
    public void updateStudent(Student student) {
        repository.updateStudent(student);
    }
    
    public void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }   
}
