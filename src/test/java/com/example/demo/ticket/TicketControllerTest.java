package com.example.demo.ticket;

import com.example.demo.ticket.dto.TicketRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketService ticketService;

    @Test
    @DisplayName("티켓 저장 테스트")
    void saveTicket() throws Exception {

        TicketRequest.Save request = TicketRequest.Save.builder()
                .airplaneInfoId(1L)
                .seatClass("ECONOMY")
                .seatNo("1A")
                .price(5000)
                .build();

        String content = objectMapper.writeValueAsString(request);

        given(ticketService.save(any(), anyLong())).willReturn(1L);

        mockMvc.perform(post("/airplane-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    @DisplayName("티켓 리스트 불러오는 테스트")
    void getTicketList() throws Exception {

    }
}