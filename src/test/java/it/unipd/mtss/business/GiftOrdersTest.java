////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GiftOrdersTest {
    GiftOrder gift = null;
    User user = null;

    @Before
    public void beforeTests() {
        // Arrange
        user = new User(31, "Alessio", "Turetta", LocalDate.of(2008, 6, 10));
        gift = new GiftOrder();
    }

    @Test
    public void testCanBeGifted() {
        // Act + Assert
        assertTrue(gift.canBeGifted(user, LocalTime.of(18, 15)));
    }

    @Test
    public void testCanBeGifted_WrongAge() {
        // Act
        user = new User(31, "Alessio", "Turetta", LocalDate.of(2001, 6, 10));
        // Assert
        assertFalse(gift.canBeGifted(user, LocalTime.of(18, 15)));
    }

    @Test
    public void testCanBeGifted_WrongTimestamp() {
        // Act
        user = new User(31, "Alessio", "Turetta", LocalDate.of(2008, 6, 10));
        // Assert
        assertFalse(gift.canBeGifted(user, LocalTime.of(13, 15)));
    }

    @Test
    public void testCanBeGifted_TooManyUsers() throws IllegalArgumentException {
        // Act
        for(int i=0; i<10; i++) {
            gift.users.add(user);
        }
        // Assert
        assertFalse(gift.makeGift(user, LocalTime.of(18, 15)));
    }

    @Test
    public void testMakeGift() throws IllegalArgumentException{
        // Act + Assert
        assertTrue(gift.makeGift(user, LocalTime.of(18, 15)));
    }

    @Test
    public void testMakeGift_NullUser() {
        // Act
        user = null;
        try {
            gift.makeGift(user, LocalTime.of(18, 15));
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("User cannot be null", e.getMessage());
        }
    }

    @Test
    public void testMakeGift_NullTimestamp() {
        // Act
        try {
            gift.makeGift(user, null);
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("Timestamp cannot be null", e.getMessage());
        }
    }
}