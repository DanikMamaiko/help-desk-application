package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.Attachment;
import com.example.OrdersIntership.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("SELECT a FROM Attachment a")
    public List<Attachment> getAllAttachments();

    @Query("SELECT a FROM Attachment a WHERE a.id = :id ")
    public Optional <Attachment> getAttachmentById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO attachment (blobb, ticket_id, name) VALUES (:blobb, :ticket, :name)", nativeQuery = true)
    public void saveAttachment(@Param("blobb") String blobb,
                               @Param("ticket") Long ticket,
                               @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE Attachment a SET a.blobb = :blobb, a.ticket = :ticket, a.name = :name WHERE a.id = :id")
    public void updateAttachment(@Param("id") Long id,
                                 @Param("blobb") String blobb,
                                 @Param("ticket") Ticket ticket,
                                 @Param("name") String name);

    @Modifying
    @Query("DELETE FROM Attachment a WHERE a.id = :id")
    public void deleteAttachment(@Param("id") Long id);

}
