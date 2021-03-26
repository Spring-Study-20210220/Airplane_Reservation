package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.Request;
import com.example.demo.airplane.info.dto.Response;
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


@WebMvcTest(controllers = AirplaneInfoController.class)
public class AirplaneInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AirplaneInfoService airplaneInfoService;

    @Test
    @DisplayName("비행기 정보 저장")
    void saveAirplaneInfo() throws Exception {

        String departure = "서울";
        String arrival = "미국";
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        Request.Save save = new Request.Save(departure, arrival, date, time);
        String request = objectMapper.writeValueAsString(save);

        given(airplaneInfoService.save(any())).willReturn(1L);

        mockMvc.perform(post("/airplaneinfo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(airplaneInfoService, times(1)).save(any());
    }

    @Test
    @DisplayName("비행기 정보 가져오기")
    void getAirplaneInfo() throws Exception {
        AirplaneInfo mockAirplaneInfo = new AirplaneInfo(
                1L,
                "Seoul",
                "Busan",
                LocalDate.now(),
                LocalTime.now()
        );

        given(airplaneInfoService.getAirplaneInfo(any())).willReturn(Response.AirplaneInfo.of(mockAirplaneInfo));

        mockMvc.perform(get("/airplaneinfo?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure").value("Seoul"))
                .andExpect(jsonPath("$.arrival").value("Busan"));
    }

}
