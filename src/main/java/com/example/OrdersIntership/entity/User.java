package com.example.OrdersIntership.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"historyList", "commentList", "feedbackList", "assigneeList", "ownerList", "approverList"})
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_id")
    private RoleEnum role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set <History> historyList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set <Comment> commentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set <Feedback> feedbackList;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private Set <Ticket> assigneeList;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set <Ticket> ownerList;

    @OneToMany(mappedBy = "approver", cascade = CascadeType.ALL)
    private Set <Ticket> approverList;

}
