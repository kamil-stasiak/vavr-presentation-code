package pipeline;

import lombok.Value;

@Value
class CustomerDetailedDto {
    String name;
    String age;
    CustomerDetailedDto(CustomerDto customerDto) {
        this.name = customerDto.getFullName();
        this.age = "18";
    }
}
