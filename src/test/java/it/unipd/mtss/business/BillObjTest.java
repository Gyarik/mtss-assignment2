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

    @Test
    public void testGetSum_ProcessorDiscount() throws BillException {
        // Act
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "AMD Ryzen 5 5600X", 299.99));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "AMD Ryzen 7 5800X", 449.50));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "AMD Ryzen 5 4500", 129.22));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "Intel i3 10400", 182.00));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "Intel i5 10700K", 374.15));
        itemsOrdered.add(new EItem(EItem.category.PROCESSOR, "Intel i9 10900K", 488.33));
        // Assert
        assertEquals(1858.58, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void testGetSum_MouseGift() throws BillException {
        // Act
        for(int i=0; i<11; i++) {
            itemsOrdered.add(new EItem(EItem.category.MOUSE, "Logitech", i + 10.22));
        }
        // Assert
        assertEquals(157.2, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void testGetSum_MouseEqualsKeyboard() throws BillException {
        // Act
        for(int i=0; i<5; i++) {
            itemsOrdered.add(new EItem(EItem.category.MOUSE, "Logitech", i + 10.22));
        }
        for(int i=0; i<5; i++) {
            itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "HyperX", i + 123.50));
        }
        // Assert
        assertEquals(678.38, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void testGetSum_Discount1000() throws BillException {
        // Act
        for(int i=0; i<10; i++) {
            itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "ASUS", 110.0));
        }
        // Assert
        assertEquals(990.0, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }

    @Test
    public void testGetSum_TooManyItems() {
        // Act
        for(int i=0; i<31; i++) {
            itemsOrdered.add(new EItem(EItem.category.KEYBOARD, "ASUS", 110.0));
        }
        try {
            bill.getOrderPrice(itemsOrdered, user);
        } catch(BillException e) {
            // Assert
            assertEquals("There cannot be more than 30 items", e.getMessage());
        }
    }

    @Test
    public void testGetSum_Fee() throws BillException {
        // Act
        itemsOrdered.add(new EItem(EItem.category.MOUSE, "Logitech", 9.51));
        // Assert
        assertEquals(11.51, bill.getOrderPrice(itemsOrdered, user), 1e-4);
    }
}