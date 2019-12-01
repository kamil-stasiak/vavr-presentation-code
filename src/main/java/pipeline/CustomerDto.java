package pipeline;

import lombok.Value;

@Value
class CustomerDto {
    private String fullName;

    CustomerDto(String name) {
        this.fullName = name;
    }
}
