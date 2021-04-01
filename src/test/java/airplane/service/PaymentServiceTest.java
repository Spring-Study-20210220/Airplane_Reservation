package airplane.service;


import airplane.domain.Payment;
import airplane.domain.PaymentStatus;
import airplane.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;
    private static Long reservationId = 1L;
    private static Long userId = 1L;

    @BeforeEach
    void setUp() {
        payment = Payment.createBuilder()
                .amount(1000)
                .account("결제정보")
                .build();
    }

    @Test
    void 결제한다() {
        //given
        given(paymentRepository.save(any())).willReturn(payment);

        //when
        Payment savePayment = paymentService.save(payment, reservationId, userId);

        //then
        assertThat(savePayment.getAmount()).isEqualTo(payment.getAmount());
        assertThat(savePayment.getAccount()).isEqualTo(payment.getAccount());
        assertThat(savePayment.getStatus()).isEqualTo(PaymentStatus.PAYED);
        assertThat(savePayment.getUser().getId()).isEqualTo(userId);
        assertThat(savePayment.getUser().getMileage()).isEqualTo(payment.getAmount());
        assertThat(savePayment.getAmount()).isEqualTo(payment.getAmount());


    }

    @Test
    void 결제취소한다() {

    }


}
