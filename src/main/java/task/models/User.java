package task.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private byte age;

    @Column
    private String email;

    public User() {

    }

    public User(String name, String lastname, byte age, String email) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User#" + id + ": " + name + " " + lastname + " " + age + ", email: " + email;
    }
}
