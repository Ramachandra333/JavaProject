package com.backend.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import static org.hibernate.annotations.SourceType.DB;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email address")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Employee employee;

    @CreationTimestamp(source = DB)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp(source = DB)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
