//package com.example.OrdersIntership.controllers.mvc;
//
//
//import com.example.OrdersIntership.dto.CategoryDto;
//import com.example.OrdersIntership.dto.TicketDto;
//import com.example.OrdersIntership.dto.UserDto;
//import com.example.OrdersIntership.entity.StateEnum;
//import com.example.OrdersIntership.entity.UrgencyEnum;
//import com.example.OrdersIntership.exceptions.NoSuchUserException;
//import com.example.OrdersIntership.services.CategoryServiceImpl;
//import com.example.OrdersIntership.services.TicketServiceImpl;
//import com.example.OrdersIntership.services.UserServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.security.auth.login.AccountNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequiredArgsConstructor
//public class TicketController {
//
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TicketController.class);
//
//    private final TicketServiceImpl ticketService;
//    private final CategoryServiceImpl categoryService;
//    private final UserServiceImpl userService;
//
//    List<String> actionsList = null;
//
//    {
//        actionsList = new ArrayList<>();
//        actionsList.add("Create");
//        actionsList.add("Submit");
//        actionsList.add("Approve");
//        actionsList.add("Decline");
//        actionsList.add("Cancel");
//        actionsList.add("Assign to Me");
//        actionsList.add("Done");
//    }
//
//    @GetMapping("/allTickets")
//    public String getAllTickets(Model model
//                                //,@AuthenticationPrincipal UserDetails userDetails
//                                ) throws AccountNotFoundException {
//
//
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // RETURN STRING, NEED RETURN OBJECT!!
//        logger.info("Authentication.getPrincipal() returns:    {}", authentication.getPrincipal());
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//
//        if (userDetails == null) {
//            throw new AccountNotFoundException("Account Error");
//        }
//
//        String email = userDetails.getUsername();
//        Optional<UserDto> user = userService.getUserByEmail(email);
//
//        List<TicketDto> myTicketsList = ticketService.findTicketsByOwner(user.get().getId());
//        List<TicketDto> ticketList = ticketService.findAllTickets();
//
//        model.addAttribute("tickets", ticketList);
//        model.addAttribute("myTicketsList", myTicketsList);
//
//        model.addAttribute("actionsList", actionsList);
//
//        return "allTickets";
//    }
//
//    @GetMapping("/createTicket")
//    public String createTicket(Model model){
//
//        TicketDto ticket = new TicketDto();
//        model.addAttribute("ticket", ticket);
//
//        List <CategoryDto> listCategories = categoryService.findAllCategories();
//        model.addAttribute("categories", listCategories);
//
//        model.addAttribute("urgencies", UrgencyEnum.values());
//
//        return "createTicket";
//    }
//
//    @PostMapping("/saveTicket")
//    public String saveTicket(@ModelAttribute("ticket") TicketDto ticket,
//                             @RequestParam("category.id") Long categoryId,
//                             @AuthenticationPrincipal UserDetails userDetails) throws NoSuchUserException {
//
//        CategoryDto selectedCategory = categoryService.findCategoryById(categoryId);
//        ticket.setCategory(selectedCategory);
//
//        ticket.setState(StateEnum.NEW);
//
//        Optional<UserDto> userDto = userService.getUserByEmail(userDetails.getUsername());
//        if(userDto.isPresent()){
//            ticket.setAssignee(userDto.get());
//        } else {
//            throw new NoSuchUserException("User doesn't exist");
//        }
//
//        ticketService.createTicket(ticket);
//
//        return "redirect:/allTickets";
//
//    }
//
//    @GetMapping("/updateTicket")
//    public String editTicket(){
//
//        return "ticket/editTickets";
//    }
//
//    @GetMapping("/test")
//    public String testController(){
//        return "test";
//    }
//
//}
