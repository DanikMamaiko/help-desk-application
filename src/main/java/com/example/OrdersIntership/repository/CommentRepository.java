package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.Comment;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c")
    public List<Comment> getAllComments();

    @Query("SELECT c FROM Comment c WHERE c.id = :id")
    public Optional <Comment> getCommentById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO comment  (user_id, text, date, ticket_id) VALUES (:user, :text, :date, :ticket)", nativeQuery = true)
    public void saveComment(@Param("user") Long user,
                            @Param("text") String text,
                            @Param("date") LocalDate date,
                            @Param("ticket") Long ticket);

    @Modifying
    @Query("UPDATE Comment c SET c.user = :user, c.text = :text, c.date = :date, c.ticket = :ticket WHERE c.id = :id")
    public void updateComment(@Param("id") Long id,
                              @Param("user") User user,
                              @Param("text") String text,
                              @Param("date") LocalDate date,
                              @Param("ticket") Ticket ticket);
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.id = :id")
    public void deleteComment(@Param("id") Long id);

}
