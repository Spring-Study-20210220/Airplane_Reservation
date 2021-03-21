package com.example.demo.airplane.info;

import com.example.demo.airplane.info.dto.ControllerRequest;
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

        ControllerRequest.Save save = new ControllerRequest.Save(departure, arrival, date, time);
        String request = objectMapper.writeValueAsString(save);

        given(airplaneInfoService.save(any())).willReturn(new Response.Save(1L));

        mockMvc.perform(post("/airplaneinfo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(airplaneInfoService, times(1)).save(any());
    }

    @Test
    @DisplayName("비행기 정보 업데이트")
    void updateAirplaneInfo() {

    }

    @Test
    @DisplayName("비행기 정보 가져오기")
    void getAirplaneInfo() {

    }

    @Test
    @DisplayName("비행기 정보 목록 가져오기")
    void getAirplaneInfoList() {

    }

}
