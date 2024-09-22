package com.example.OrdersIntership.service;

import com.example.OrdersIntership.dto.TicketDto;
import com.example.OrdersIntership.entity.Ticket;
import com.example.OrdersIntership.mapper.TicketMapper;
import com.example.OrdersIntership.repository.TicketRepository;
import com.example.OrdersIntership.service.interfaces.TicketServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketServiceInterface {


    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    //private final CurrentUserService currentUserService;
    //private @NonNull asdas

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> findAllTickets() {
        return ticketRepository.getAllTickets()
                .stream()
                .map(ticketMapper::toTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TicketDto findTicketById(Long id) {
        return ticketRepository.getTicketById(id)
                .map(ticketMapper::toTicketDto)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found"));
    }

    @Override
    @Transactional
    public void createTicket(TicketDto entity) {
        Ticket ticket = ticketMapper.toTicket(entity);
        ticketRepository.saveTicket(ticket.getName(), ticket.getDescription(), ticket.getCreatedOn(), ticket.getDesiredResolutionDate(),
                ticket.getAssignee().getId(), ticket.getOwner().getId(), ticket.getState(), ticket.getUrgency(), ticket.getApprover().getId());
    }

    @Override
    @Transactional
    public void updateTicket(TicketDto entity) {
        Ticket ticket = ticketMapper.toTicket(entity);
        ticketRepository.updateTicket(ticket.getId(), ticket.getName(), ticket.getDescription(), ticket.getCreatedOn(), ticket.getDesiredResolutionDate(),
                ticket.getAssignee(), ticket.getOwner(), ticket.getState(), ticket.getUrgency(), ticket.getApprover());
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        ticketRepository.deleteTicket(id);
    }


//    @Transactional
//    public List <TicketDto> getAllTickets(){
//
//        List<Ticket> response = null;
//
//        UserDto userDto = currentUserService.getCurrentUser();
//        Long userId = userDto.getId();
//
//        if(userDto.getRole().equals(RoleEnum.EMPLOYEE)){
//
//            response = ticketRepository.getAllTicketsCreatedByPerson(userId);
//
//        } else if (userDto.getRole().equals(RoleEnum.MANAGER)) {
//
//            List <Ticket> getAllTicketsCreatedByPerson = ticketRepository.getAllTicketsCreatedByPerson(userId);
//            List <Ticket> getTicketsCreatedByAllEmployeesInNew = ticketRepository.getTicketsCreatedByAllEmployeesInNew();
//            List <Ticket> getTicketsWhichHaveHimAsAnApproverInStatuses = ticketRepository.getTicketsWhichHaveHimAsAnApproverInStatuses(userId);
//
//            response.addAll(getAllTicketsCreatedByPerson);
//            response.addAll(getTicketsCreatedByAllEmployeesInNew);
//            response.addAll(getTicketsWhichHaveHimAsAnApproverInStatuses);
//
//        } else {
//
//            List <Ticket> getTicketsInStatusApprovedCreatedByEmployeeAndManagers = ticketRepository.getTicketsInStatusApprovedCreatedByEmployeeAndManagers();
//            List <Ticket> getTicketsMarkedHimAsAssigneeInStatusesProgressDone = ticketRepository.getTicketsMarkedHimAsAssigneeInStatusesProgressDone(userId);
//
//            response.addAll(getTicketsInStatusApprovedCreatedByEmployeeAndManagers);
//            response.addAll(getTicketsMarkedHimAsAssigneeInStatusesProgressDone);
//
//        }
//
//        return response.stream()
//                .map(ticketMapper::toTicketDto)
//                .collect(Collectors.toList());
//    }

//    @Override
//    @Transactional
//    public List<TicketDto> findTicketsByOwner(Long ownerId) {
//        return ticketRepository.findTicketsByOwner(ownerId)
//                .stream().map(ticketMapper::toTicketDto)
//                .collect(Collectors.toList());
//    }

}
