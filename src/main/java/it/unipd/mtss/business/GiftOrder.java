////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiftOrder {
    Random rand;
    List<User> users;

    public GiftOrder() {
        rand = new Random();
        rand.setSeed(10);
        users = new ArrayList<>();
    }

    public boolean canBeGifted(User user, LocalTime timeStamp) {
        // Controlla se lo user e' minorenne
        if(Period.between(user.getBirthDate(), LocalDate.now()).getYears() < 18) {
            // Controlla se l'ordine e' stato eseguito tra le 18 e le 19
            if(timeStamp.isAfter(LocalTime.of(18, 0)) && timeStamp.isBefore(LocalTime.of(19, 0))) {
                // Controlla se non ci sono gia' 10 user selezionati
                if(users.size() < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean makeGift(User user, LocalTime timeStamp) {
        // Lancia un'eccezione se:
        // - user e' nullo
        // - timeStamp e' nullo
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(timeStamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        // se lo user puo' partecipare al giveaway e viene selezionato casualmente aggiungilo alla lista
        if(canBeGifted(user, timeStamp) && rand.nextInt(100) <= 25) {
            users.add(user);
            return true;
        }
        return false;
    }
}