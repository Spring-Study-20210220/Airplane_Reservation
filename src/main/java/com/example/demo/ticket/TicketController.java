package com.example.demo.ticket;

import com.example.demo.ticket.dto.TicketRequest;
import com.example.demo.ticket.dto.TicketResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airplane-ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("")
    public ResponseEntity<TicketResponse.Save> saveAirplaneTicket(@Valid @RequestBody TicketRequest.Save saveDto) {

        Long result = ticketService.save(saveDto.toEntity(saveDto), saveDto.getAirplaneInfoId());

        return ResponseEntity.ok(new TicketResponse.Save(result));
    }

    @GetMapping("/{airplaneinfo-id}")
    public ResponseEntity<?> getAirplaneTicketList() {

        return null;
    }
}
