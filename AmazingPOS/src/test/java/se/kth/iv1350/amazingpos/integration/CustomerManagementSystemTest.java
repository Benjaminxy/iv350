package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;

/**
 * Tests for the CustomerManagementSystem class.
 */
public class CustomerManagementSystemTest {
    private CustomerManagementSystem instance;

    @BeforeEach
    public void setUp() {
        instance = new CustomerManagementSystem();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of registerCustomer method, of class CustomerManagementSystem.
     * Ensures that a customer can be successfully registered.
     */
    @Test
    public void testRegisterCustomer() {
        System.out.println("registerCustomer");
        String customerId = "123";
        boolean isMember = true;
        CustomerDTO customerDTO = new CustomerDTO(customerId, isMember);
        instance.registerCustomer(customerDTO);
        CustomerDTO result = instance.getCustomer(customerId);
        assertNotNull(result, "Customer should be found in system after being registered.");
    }

    /**
     * Test of getCustomer method, of class CustomerManagementSystem.
     * Ensures that the correct customer is retrieved, and null is returned when no customer is found.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        String existingCustomerId = "456";
        boolean isMember = true;
        CustomerDTO existingCustomer = new CustomerDTO(existingCustomerId, isMember);
        instance.registerCustomer(existingCustomer);
        
        // Test retrieving an existing customer
        CustomerDTO result = instance.getCustomer(existingCustomerId);
        assertEquals(existingCustomer, result, "Retrieved customer should match the one registered.");

        // Test retrieving a non-existing customer
        String nonExistingCustomerId = "999";
        CustomerDTO nonExistingResult = instance.getCustomer(nonExistingCustomerId);
        assertNull(nonExistingResult, "Should return null for non-existing customer ID.");
    }
}
