package bh.beca.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import bh.beca.demo.dto.CustomerDto;
import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.mapper.CustomerMapper;
import bh.beca.demo.mapper.SummaryMapper;
import bh.beca.demo.model.SummaryView;
import bh.beca.demo.model.UserEntity;
import bh.beca.demo.repository.CustomerSummaryRepository;
import bh.beca.demo.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerSummaryRepository customerSummaryRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    SummaryMapper summaryMapper = new SummaryMapper();

    @Spy
    CustomerMapper customerMapper = new CustomerMapper();

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void getSummaryEmpty() {
        Optional<CustomerSummaryDto> optionalSummary = customerService.getSummary(13L);
        assertThat(optionalSummary).isEmpty();
    }

    @Test
    void getSummary() {
        when(customerSummaryRepository.viewById(13L)).thenReturn(List.of(
                new SummaryView(13L, "Fn", "Ln", 56L, "An56", new BigDecimal("243"), 21L, LocalDateTime.now(), new BigDecimal("221"), "Tc21"),
                new SummaryView(13L, "Fn", "Ln", 56L, "An56", new BigDecimal("243"), 22L, LocalDateTime.now(), new BigDecimal("222"), "Tc22"),
                new SummaryView(13L, "Fn", "Ln", 57L, "An57", new BigDecimal("0"), null, null, null, null)
        ));

        Optional<CustomerSummaryDto> optionalSummary = customerService.getSummary(13L);
        assertThat(optionalSummary).isNotEmpty();

        CustomerSummaryDto actual = optionalSummary.get();
        assertThat(actual.getId()).isEqualTo(13L);
        assertThat(actual.getAccounts()).hasSize(2);
        assertThat(actual.getAccounts().get(0).getTransactions()).hasSize(2);
        assertThat(actual.getAccounts().get(1).getTransactions()).isEmpty();
    }

    @Test
    void getSummaryNoAccounts() {
        when(customerSummaryRepository.viewById(14L)).thenReturn(List.of(
                new SummaryView(14L, "Fn", "Ln", null, null, null, null, null, null, null)
        ));

        Optional<CustomerSummaryDto> optionalSummary = customerService.getSummary(14L);
        assertThat(optionalSummary).isNotEmpty();

        CustomerSummaryDto actual = optionalSummary.get();
        assertThat(actual.getId()).isEqualTo(14L);
        assertThat(actual.getAccounts()).isEmpty();
    }

    @Test
    void getAllEmpty() {
        List<CustomerDto> customers = customerService.getAll();
        assertThat(customers).isEmpty();
    }

    @Test
    void getAll() {
        UserEntity user = new UserEntity();
        user.setId(76L);
        user.setFirstName("Green");
        user.setLastName("Forest");

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<CustomerDto> customers = customerService.getAll();
        assertThat(customers).hasSize(1)
                .extracting(CustomerDto::getId, CustomerDto::getFirstName, CustomerDto::getLastName)
                .containsExactly(Tuple.tuple(76L, "Green", "Forest"));
    }
}