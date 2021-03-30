package com.example.demo.ticket;

import com.example.demo.ticket.dto.TicketRequest;
import com.example.demo.ticket.dto.TicketResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("티켓 저장 테스트")
    void saveTicket() {
        //id값이 6이 나오면 성공
        TicketRequest.Save saveDto = new TicketRequest.Save(1L, "ECONOMY", "1A", 5000);

        HttpEntity<TicketRequest.Save> request = new HttpEntity<>(saveDto);

        ResponseEntity<TicketResponse.Save> responseEntity = restTemplate
                .postForEntity("/ticket", request, TicketResponse.Save.class);

        TicketResponse.Save body = responseEntity.getBody();

        Assertions.assertThat(body.getId()).isEqualTo(6L);
    }

    @Test
    @DisplayName("airplaneId로 티켓 리스트 불러오는 테스트")
    void getTicketList() {

        ResponseEntity<TicketResponse.TicketListInfo> responseEntity
                = restTemplate.getForEntity("/ticket/{airplane_id}/list", TicketResponse.TicketListInfo.class, 1L);

        Assertions.assertThat(responseEntity.getBody().getList().size()).isEqualTo(5);
    }

}
