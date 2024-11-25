package bh.beca.demo.web;

import bh.beca.demo.dto.CustomerDto;
import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("api/v1/customers")
    public List<CustomerDto> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping("api/v1/customers/{id}")
    public CustomerSummaryDto getSummary(@PathVariable("id") long id) {
        return customerService.getSummary(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer by id: " + id));
    }
}
