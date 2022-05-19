////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User user = null;

    @Before
    public void beforeTests() {
        // Arrange
        user = new User(31, "Alessio", "Turetta", LocalDate.of(2001, 10, 6));
    }

    @Test
    public void testNullBirthDate() {
        // Act
        try {
            user = new User(31, "Alessio", "Turetta", null);
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("Date of birth cannot be null", e.getMessage());
        }
    }

    @Test
    public void testBirthDate_OutOfRange() {
        // Act
        try {
            user = new User(31, "Alessio", "Turetta", LocalDate.of(2084, 1, 1));
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("Date of birth must be before today's date", e.getMessage());
        }
    }

    @Test
    public void testId_OutOfRange() {
        // Act
        try {
            user = new User(100001, "Alessio", "Turetta", LocalDate.of(2001, 10, 6));
        } catch(IllegalArgumentException e) {
            // Assert
            assertEquals("ID cannot be more than 100000", e.getMessage());
        }
    }

    @Test
    public void testGetBirthDate() {
        // Act + Assert
        assertEquals(LocalDate.of(2001, 10, 6), user.getBirthDate());
    }
}
