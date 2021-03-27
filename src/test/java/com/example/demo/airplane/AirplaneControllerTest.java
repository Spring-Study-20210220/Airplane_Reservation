package com.example.demo.airplane;

import com.example.demo.airplane.dto.Request;
import com.example.demo.airplane.dto.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AirplaneController.class)
public class AirplaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AirplaneService airplaneService;

    @Test
    @DisplayName("비행기 정보 저장")
    void saveAirplane() throws Exception {

        String departure = "서울";
        String arrival = "미국";
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        Request.SaveDto save = new Request.SaveDto(departure, arrival, date, time);
        String request = objectMapper.writeValueAsString(save);

        given(airplaneService.save(any())).willReturn(1L);

        mockMvc.perform(post("/airplaneinfo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(airplaneService, times(1)).save(any());
    }

    @Test
    @DisplayName("비행기 정보 가져오기")
    void getAirplane() throws Exception {
        Airplane mockAirplane = new Airplane(
                1L,
                "Seoul",
                "Busan",
                LocalDate.now(),
                LocalTime.now(),
                20,
                20,
                20
        );

        given(airplaneService.getAirplane(any())).willReturn(Response.AirplaneDto.of(mockAirplane));

        mockMvc.perform(get("/airplaneinfo?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure").value("Seoul"))
                .andExpect(jsonPath("$.arrival").value("Busan"));
    }

}
