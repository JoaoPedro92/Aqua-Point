package pt.iade.AquaPoint.models;

import java.sql.Date;

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date joined;

    // construtor
    public UserModel
    (
        int id, 
        String name, 
        String email, 
        String password, 
        Date joined
    ) 
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.joined = joined;
    }

    public int GetID() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public String GetEmail() {
        return email;
    }

    public String GetPassword() {
        return password;
    }

    public Date GetJoined() {
        return joined;
    }

    public void SetName(String newName) {
        name = newName;
    }

    public void SetPassword(String newPassword) {
        password = newPassword;
    }
}