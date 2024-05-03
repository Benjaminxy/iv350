package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

/**
 * Tests for the DiscountDatabase class.
 */
public class DiscountDatabaseTest {
    private DiscountDatabase instance;

    @BeforeEach
    public void setUp() {
        instance = new DiscountDatabase();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of calculateItemDiscount method for different items.
     */
    @Test
    public void testCalculateItemDiscount() {
        System.out.println("calculateItemDiscount");
        ItemDTO apple = new ItemDTO("001", "apple", 100, 0.25);
        ItemDTO orange = new ItemDTO("002", "orange", 80, 0.20);
        ItemDTO unknown = new ItemDTO("003", "carrot", 50, 0.10);

        double appleDiscount = instance.calculateItemDiscount(apple);
        double orangeDiscount = instance.calculateItemDiscount(orange);
        double unknownDiscount = instance.calculateItemDiscount(unknown);

        assertEquals(10.0, appleDiscount, 0.001, "Expected 10% discount on apple.");
        assertEquals(5.6, orangeDiscount, 0.001, "Expected 7% discount on orange.");
        assertEquals(0.0, unknownDiscount, 0.001, "Expected no discount on unknown item.");
    }

    /**
     * Test of calculateSaleDiscount method for different total amounts.
     */
    @Test
    public void testCalculateSaleDiscount() {
        System.out.println("calculateSaleDiscount");
        double totalSaleAmountLow = 90;  // Below discount threshold
        double totalSaleAmountHigh = 150;  // Above discount threshold

        double lowSaleDiscount = instance.calculateSaleDiscount(totalSaleAmountLow);
        double highSaleDiscount = instance.calculateSaleDiscount(totalSaleAmountHigh);

        assertEquals(0.0, lowSaleDiscount, "Expected no discount for low sales.");
        assertEquals(15.0, highSaleDiscount, "Expected 10% discount on high sales.");
    }

    /**
     * Test of getCustomerDiscount method for member and non-member customers.
     */
    @Test
    public void testGetCustomerDiscount() {
        System.out.println("getCustomerDiscount");
        CustomerDTO member = new CustomerDTO("customer1", true);
        CustomerDTO nonMember = new CustomerDTO("customer2", false);

        double memberDiscount = instance.getCustomerDiscount(member);
        double nonMemberDiscount = instance.getCustomerDiscount(nonMember);

        assertEquals(0.15, memberDiscount, "Expected 15% discount for members.");
        assertEquals(0.0, nonMemberDiscount, "Expected no discount for non-members.");
    }
}
