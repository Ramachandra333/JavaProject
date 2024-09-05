package com.backend.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import static org.hibernate.annotations.SourceType.DB;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "role")
    private String role;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "manager")
    private String manager;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @CreationTimestamp(source = DB)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp(source = DB)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
