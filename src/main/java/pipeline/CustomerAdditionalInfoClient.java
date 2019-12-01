package pipeline;

class CustomerAdditionalInfoClient {
    CustomerDetailedDto getDetailedInfo(CustomerDto customerDto) {
        return new CustomerDetailedDto(customerDto);
    }
}
