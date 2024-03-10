package com.codegym.maccenter.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{error.invalid_email}")
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)    // CascadeType.MERGE: có khả năng xóa bên khóa chính
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(
                    name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn (
                    name = "ROLE_ID", referencedColumnName = "ID")}
            )
    private List<Role> roles;

    //custom lai constructor
    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();




    }






}//create table mapping trong db
