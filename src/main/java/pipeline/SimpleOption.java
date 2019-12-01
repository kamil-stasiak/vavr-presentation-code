package pipeline;

import io.vavr.control.Option;

public class SimpleOption {
    CustomerAssembler customerAssembler;
    CustomerAdditionalInfoClient customerAdditionalInfoClient;
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SimpleOption simpleOption = new SimpleOption();

        CustomerDetailedDto allCustomerData = simpleOption.getDetailedCustomerDataController1("UUID: 123");
        System.out.println(allCustomerData);
        ;
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
