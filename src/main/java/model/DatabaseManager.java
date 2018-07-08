package model;

public class DatabaseManager {
    public boolean checkUser(String userName, String password){
        if(userName.equals("tuananh") && password.equals("tuananh")){
            return true;
        }
        else{
            return false;
        }
    }
}
