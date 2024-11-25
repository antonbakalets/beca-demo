package bh.beca.demo.service;

import bh.beca.demo.dto.CustomerDto;
import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.mapper.CustomerMapper;
import bh.beca.demo.mapper.SummaryMapper;
import bh.beca.demo.model.SummaryView;
import bh.beca.demo.model.UserEntity;
import bh.beca.demo.repository.CustomerSummaryRepository;
import bh.beca.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSummaryRepository customerSummaryRepository;
    private final SummaryMapper summaryMapper;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerSummaryDto> getSummary(long id) {
        log.debug("Creating summary for customer id: {}", id);
        List<SummaryView> view = customerSummaryRepository.viewById(id);
        return summaryMapper.map(view);
    }

    @Override
    public List<CustomerDto> getAll() {
        log.debug("Getting all customers");
        List<UserEntity> users = userRepository.findAll();
        return customerMapper.map(users);
    }
}
