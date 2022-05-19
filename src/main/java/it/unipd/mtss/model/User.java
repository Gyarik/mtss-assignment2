////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {
    int id;
    private String name, surname;
    private LocalDate birthDate;

    public User(int id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        if(birthDate == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        if(id > 100000) {
            throw new IllegalArgumentException("ID cannot be more than 100000");
        }
        if(birthDate.isBefore(LocalDate.now())) {
            this.birthDate = birthDate;
        } else {
            throw new IllegalArgumentException("Date of birth must be before today's date");
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
