package com.jsa.service;


import com.jsa.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jsa.repository.UserAccRepository;

@Service
public class DatabaseManager {

    @Autowired
    private UserAccRepository userAccRepository;

    public boolean checkUser(String userName, String password) {
        UserAccount userAccount = userAccRepository.findByUserName(userName);
        if(userAccount != null) {
            if (password.equals(userAccount.getPassword())) {
                return true;
            }
        }
        return false;
//        if(userName.equals("tuananh") && password.equals("tuananh")){
//            return true;
//        }
//        return false;
    }
}
