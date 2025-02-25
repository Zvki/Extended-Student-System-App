package polsl.bartosz.sosnica.fullstack_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public UserModel(String password, String name, String surname, String email) {
        this.createdAt = LocalDateTime.now();
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = null;
        this.status = Status.Inactive;
    }

    public enum Role {
        Student, Teacher, Admin
    }

    public enum Status {
        Active, Inactive
    }

}
