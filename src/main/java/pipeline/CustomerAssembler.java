package pipeline;

public class CustomerAssembler {
    public CustomerDto toDto(CustomerDs customerDs) {
        return new CustomerDto(customerDs.getName());
    }
}
