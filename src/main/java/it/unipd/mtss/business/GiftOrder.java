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
    int count;

    public GiftOrder() {
        rand = new Random();
        users = new ArrayList<>();
    }

    public boolean canBeGifted(User user, LocalTime timeStamp) {
        if(Period.between(user.getBirthDate(), LocalDate.now()).getYears() < 18) {
            if(timeStamp.isAfter(LocalTime.of(18, 0)) && timeStamp.isBefore(LocalTime.of(19, 0))) {
                if(users.size() < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean makeGift(User user, LocalTime timeStamp) {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(timeStamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        if(canBeGifted(user, timeStamp)) { //&& rand.nextInt(100) < 30
            users.add(user);
            return true;
        }
        return false;
    }
}