package pipeline;

import io.vavr.control.Option;

public class SimpleOption {
    CustomerAssembler customerAssembler = new CustomerAssembler();
    CustomerAdditionalInfoClient customerAdditionalInfoClient = new CustomerAdditionalInfoClient();
    CustomerRepository customerRepository = new CustomerRepository();

    public static void main(String[] args) {
        SimpleOption simpleOption = new SimpleOption();

        CustomerDetailedDto allCustomerData = simpleOption.getDetailedCustomerDataController3("UUID: 123");
        System.out.println(allCustomerData);
    }

    @SomeAddnotaion
    private CustomerDetailedDto getDetailedCustomerDataController1(String customerID) {
        return customerAdditionalInfoClient.getDetailedInfo(customerAssembler.toDto(customerRepository.find(customerID)));
    }

    @SomeAddnotaion
    private CustomerDetailedDto getDetailedCustomerDataController2(String customerID) {
        CustomerDs customerDs = customerRepository.find(customerID);
        CustomerDto customerDto = customerAssembler.toDto(customerDs);
        return customerAdditionalInfoClient.getDetailedInfo(customerDto);
    }

    @SomeAddnotaion
    // Pipeline pattern
    private CustomerDetailedDto getDetailedCustomerDataController3(String customerID) {
        return Option.of(customerID)
                .map(customerRepository::find)
                .map(customerAssembler::toDto)
                .map(customerAdditionalInfoClient::getDetailedInfo)
                .getOrNull();
    }
}
