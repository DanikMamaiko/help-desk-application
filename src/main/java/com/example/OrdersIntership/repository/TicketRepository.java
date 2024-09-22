package com.example.OrdersIntership.repository;

import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.entity.User;
import com.example.OrdersIntership.entity.StateEnum;
import com.example.OrdersIntership.entity.UrgencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t")
    public List<Ticket> getAllTickets();

    @Query("SELECT t FROM Ticket t WHERE t.id = :id")
    public Optional <Ticket> getTicketById(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO ticket (name, description, created_on, desired_resolution_date, assignee_id, owner_id, state_id, urgency_id, approver_id) " +
            "VALUES (:name, :description, :createdOn, :desiredResolutionDate, :assignee, :owner, :state, :urgency, :approver)", nativeQuery = true)
    public void saveTicket(@Param("name") String name,
                           @Param("description") String description,
                           @Param("createdOn") LocalDate createdOn,
                           @Param("desiredResolutionDate") LocalDate desiredResolutionDate,
                           @Param("assignee") Long assignee,
                           @Param("owner") Long owner,
                           @Param("state") StateEnum state,
                           @Param("urgency") UrgencyEnum urgency,
                           @Param("approver") Long approver);

    @Modifying
    @Query("UPDATE Ticket t SET t.name = :name, t.description = :description, t.createdOn = :createdOn, t.desiredResolutionDate = :desiredResolutionDate," +
            "t.assignee = :assignee, t.owner = :owner, t.state = :state, t.urgency = :urgency, t.approver = :approver WHERE t.id = :id")
    public void updateTicket(@Param("id") Long id,
                           @Param("name") String name,
                           @Param("description") String description,
                           @Param("createdOn") LocalDate createdOn,
                           @Param("desiredResolutionDate") LocalDate desiredResolutionDate,
                           @Param("assignee") User assignee,
                           @Param("owner") User owner,
                           @Param("state") StateEnum state,
                           @Param("urgency") UrgencyEnum urgency,
                           @Param("approver") User approver);

    @Modifying
    @Query("DELETE FROM Ticket t where t.id = :id")
    public void deleteTicket(@Param("id") Long id);

//    @Query("SELECT t FROM Ticket t WHERE t.owner.id = :id")
//    public List <Ticket> getAllTicketsCreatedByPerson(@Param("id") Long id);
//
//    @Query(value = "SELECT * FROM Ticket t JOIN User u on t.owner_id = u.id WHERE t.state_id = 'NEW' AND u.role_id = 'EMPLOYEE'", nativeQuery = true)
//    public List <Ticket> getTicketsCreatedByAllEmployeesInNew();
//
//    @Query(value = "SELECT * FROM ticket t WHERE t.state_id in ('APPROVED', 'DECLINED', 'INPROGRESS', 'DONE') AND approver_id = :id", nativeQuery = true)
//    public List <Ticket> getTicketsWhichHaveHimAsAnApproverInStatuses(@Param("id") Long id);
//
//    @Query(value = "SELECT * FROM Ticket t JOIN User u ON t.owner_id = u.id WHERE t.state_id = 'APPROVED' AND (u.role_id = 'EMPLOYEE' OR u.role_id = 'MANAGER')", nativeQuery = true)
//    public List<Ticket> getTicketsInStatusApprovedCreatedByEmployeeAndManagers();
//
//    @Query(value = "SELECT * FROM Ticket t WHERE t.state_id = 'INPROGRESS' OR t.state_id = 'DONE' AND t.assignee_id = :id", nativeQuery = true)
//    public List <Ticket> getTicketsMarkedHimAsAssigneeInStatusesProgressDone(@Param("id") Long id);

//    @Query("SELECT t FROM Ticket t ORDER BY t.id ASC")
//    List <Ticket> findAllSortedByIdAsc();
//
//    @Query("SELECT t FROM Ticket t ORDER BY t.id DESC")
//    List <Ticket> findAllSortedByIdDesc();
//
//    // Пример для сортировки по имени
//    @Query("SELECT t FROM Ticket t ORDER BY t.name ASC")
//    List <Ticket> findAllSortedByNameAsc();
//
//    @Query("SELECT t FROM Ticket t ORDER BY t.name DESC")
//    List <Ticket> findAllSortedByNameDesc();




}
