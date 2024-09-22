package com.example.OrdersIntership.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rate")
    private String rate;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

}
