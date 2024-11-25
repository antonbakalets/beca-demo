package bh.beca.demo.mapper;

import bh.beca.demo.dto.CustomerDto;
import bh.beca.demo.model.UserEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public List<CustomerDto> map(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::map)
                .toList();
    }

    private CustomerDto map(UserEntity userEntity) {
        return CustomerDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }
}
