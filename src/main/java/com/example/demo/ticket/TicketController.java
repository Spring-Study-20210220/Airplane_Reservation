package com.example.demo.ticket;

import com.example.demo.ticket.dto.TicketRequest;
import com.example.demo.ticket.dto.TicketResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("")
    public ResponseEntity<TicketResponse.Save> saveAirplaneTicket(@Valid @RequestBody TicketRequest.Save saveDto) {

        TicketResponse.Save result = ticketService.save(saveDto.toEntity(saveDto), saveDto.getAirplaneId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{airplane_id}/list")
    public ResponseEntity<TicketResponse.TicketListInfo> getAirplaneTicketList(
            @PathVariable(name = "airplane_id") Long airplaneId
    ) {
        return ResponseEntity.ok(ticketService.getTicketList(airplaneId));
    }
}
