package com.example.demo.ticket;

import com.example.demo.airplane.Airplane;
import com.example.demo.airplane.AirplaneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    Airplane airplane = Airplane.builder()
            .id(1L)
            .departure("서울")
            .arrival("대구")
            .takeOffDate(LocalDate.now())
            .takeOffTime(LocalTime.now())
            .businessCnt(10)
            .economyCnt(10)
            .firstCnt(0)
            .build();

    @Test
    @DisplayName("좌석 개수가 모자라는 테스트")
    void saveTicket() {
        Ticket ticket = Ticket.builder()
                .price(5000)
                .seatClass(SeatClass.FIRST)
                .seatNumber("A1")
                .build();

        given(airplaneRepository.findById(anyLong()))
                .willReturn(Optional.of(airplane));

        Assertions.assertThatThrownBy(() -> ticketService.save(ticket, 1L)).isInstanceOf(IllegalStateException.class);

        verify(airplaneRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("좌석 개수가 충분 테스트")
    void saveTicket2() {
        Ticket ticket = Ticket.builder()
                .price(5000)
                .seatClass(SeatClass.BUSINESS)
                .seatNumber("A1")
                .build();

        Ticket savedTicket = Ticket.builder()
                .id(1L)
                .price(5000)
                .seatClass(SeatClass.BUSINESS)
                .seatNumber("A1")
                .build();

        given(airplaneRepository.findById(anyLong()))
                .willReturn(Optional.of(airplane));

        given(ticketRepository.save(any())).willReturn(savedTicket);

        ticketService.save(ticket, 1L);

        verify(airplaneRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(1)).save(any());
    }

}
