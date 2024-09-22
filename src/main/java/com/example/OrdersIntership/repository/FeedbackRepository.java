package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.Feedback;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f")
    public List<Feedback> getAllFeedbacks();

    @Query("SELECT f FROM Feedback f WHERE f.id = :id")
    public Optional <Feedback> getFeedbackById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO feedback (user_id, rate, date, text, ticket_id) VALUES (:user, :rate, :date, :text, :ticket)", nativeQuery = true)
    public void saveFeedback(@Param("user") Long user,
                             @Param("rate") String rate,
                             @Param("date") LocalDate date,
                             @Param("text") String text,
                             @Param("ticket") Long ticket);

    @Modifying
    @Query("UPDATE Feedback f SET f.user = :user, f.rate = :rate, f.date = :date, f.text = :text, f.ticket = :ticket WHERE f.id = :id")
    public void updateFeedback(@Param("id") Long id,
                               @Param("user") User user,
                               @Param("rate") String rate,
                               @Param("date") LocalDate date,
                               @Param("text") String text,
                               @Param("ticket") Ticket ticket);

    @Modifying
    @Query("DELETE FROM Feedback f WHERE f.id = :id")
    public void deleteFeedback(@Param("id") Long id);

}

