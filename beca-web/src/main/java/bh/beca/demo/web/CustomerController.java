package bh.beca.demo.web;

import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("api/v1/customers/{id}")
    public CustomerSummaryDto getSummary(@PathVariable("id") long id) {
        return customerService.getSummary(id);
    }
}
