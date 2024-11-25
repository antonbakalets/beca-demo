package bh.beca.demo.service;


import bh.beca.demo.dto.CustomerDto;
import bh.beca.demo.dto.CustomerSummaryDto;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerSummaryDto> getSummary(long id);

    List<CustomerDto> getAll();
}
