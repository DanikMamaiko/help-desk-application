package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.History;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h")
    public List<History> getAllHistories();

    @Query("SELECT h FROM History h WHERE h.id = :id")
    public Optional <History> getHistoryById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO history (ticket_id, date, action, user_id, description) VALUES (:ticket, :date, :action, :user, :description)", nativeQuery = true)
    public void saveHistory(@Param("ticket") Long ticket,
                            @Param("date") LocalDate date,
                            @Param("action") String action,
                            @Param("user") Long user,
                            @Param("description") String description);

    @Modifying
    @Query("UPDATE History h SET h.ticket = :ticket, h.date = :date, h.action = :action, h.user = :user, h.description = :description WHERE h.id = :id")
    public void updateHistory(@Param("id") Long id,
                              @Param("ticket") Ticket ticket,
                              @Param("date") LocalDate date,
                              @Param("action") String action,
                              @Param("user") User user,
                              @Param("description") String description);

    @Modifying
    @Query("DELETE FROM History h WHERE h.id = :id")
    public void deleteHistory(@Param("id") Long id);

}

