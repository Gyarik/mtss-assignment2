////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EItemTest {
    EItem item = null;

    @Before
    public void beforeTests() {
        // Arrange
        item = new EItem(EItem.category.KEYBOARD, "HyperX", 123.50);
    }

    @Test
    public void testGetItemType() {
        // Act + Assert
        assertEquals(EItem.category.KEYBOARD, item.getItemType());
    }

    @Test
    public void testGetName() {
        // Act + Assert
        assertEquals("HyperX", item.getName());
    }

    @Test
    public void testGetPrice() {
        // Act + Assert
        assertEquals(123.50, item.getPrice(), 1e-4);
    }

    @Test
    public void testEItem_NegativeValue() {
        // Act
        try {
            item = new EItem(EItem.category.KEYBOARD, "HyperX", -1.00);
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("Price must be positive", e.getMessage());
        }
    }
}
