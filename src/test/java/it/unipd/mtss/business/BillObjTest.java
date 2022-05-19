////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BillObjTest {
    BillObj bill = null;
    List<EItem> itemsOrdered = null;
    User user = null;

    @Before
    public void beforeTests() {
        // Arrange
        bill = new BillObj();
        itemsOrdered = new ArrayList<EItem>();
        user = new User(31, "Alessio", "Turetta", LocalDate.of(2001, 10, 6));
    }

    @Test
    public void testGetSum() throws BillException {
        // Act
        itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "HyperX", 123.50));
        itemsOrdered.add(new EItem(EItem.category.MOTHERBOARD, "ASUS", 80.46));
        itemsOrdered.add(new EItem(EItem.category.MOUSE, "Logitech", 40.31));
        itemsOrdered.add(new EItem(EItem.category.MOUSE, "Corsair", 39.99));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "AMD", 257.89));
        // Assert
        assertEquals(542.15, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void testGetSum_NullList() throws BillException {
        // Act
        itemsOrdered = null;
        try {
            bill.getOrderPrice(itemsOrdered, user);
        } catch (BillException e) {
            // Assert
            assertEquals("Items Ordered List cannot be null", e.getMessage());
        }
    }

    @Test
    public void testGetSum_NullItem() {
        // Act
        itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "HyperX", 123.50));
        itemsOrdered.add(null);
        itemsOrdered.add(new EItem(EItem.category.MOUSE, "Logitech", 40.31));
        try {
            bill.getOrderPrice(itemsOrdered, user);
        } catch (BillException e) {
            // Assert
            assertEquals("Item cannot be null", e.getMessage());
        }
    }

    @Test
    public void testGetSum_NullUser() {
        // Act
        itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "HyperX", 123.50));
        try {
            bill.getOrderPrice(itemsOrdered, null);
        } catch (BillException e) {
            // Assert
            assertEquals("User cannot be null", e.getMessage());
        }
    }
}