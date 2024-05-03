package se.kth.iv1350.amazingpos.model;


import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;

public class Customer {
    private final CustomerDTO customerDTO;

    public Customer(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
