package com.example.OrdersIntership.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"attachmentList", "historyList", "commentList", "feedbackList"})
@ToString(exclude = {"attachmentList", "historyList", "commentList", "feedbackList"})
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "desired_resolution_date")
    private LocalDate desiredResolutionDate;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_id")
    private StateEnum state;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "urgency_id")
    private UrgencyEnum urgency;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set <Attachment> attachmentList;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set <History> historyList;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set <Comment> commentList;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set <Feedback> feedbackList;

}
