package pt.iade.AquaPoint.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private String email;
    private String password;
    private Date joined;

    // construtor para o jpa
    public User() {}

    // construtor
    public User
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getJoined() {
        return joined;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}