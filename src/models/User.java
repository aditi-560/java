package models;

public abstract class User {
    protected String username;
    String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    //just a simple string mathch
    public boolean checkPassword(String inputPassword){
        return this.password.equals(inputPassword);
    }

    public abstract String getRole(); // can be admin or student only
}


