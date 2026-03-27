package com.epharmacy.customer_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordHistory {

    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer historyId ;

    private String password;

    @Column(name = "changed_date")
    private LocalDateTime changedPasswordDate;  //Current Local Time

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
