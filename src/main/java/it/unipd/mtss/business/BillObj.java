////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.util.List;

public class BillObj implements Bill {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double tot = 0.0;
        int countProc = 0, countMouse = 0, countKey = 0;

        // Lancia un'eccezione se:
        // - itemsOrdered e' nullo o contiene item nulli
        // - user e' nullo
        // - ci sono piu' di 30 item
        if(itemsOrdered == null) {
            throw new BillException("Items Ordered List cannot be null");
        }
        if(itemsOrdered.contains(null)) {
            throw new BillException("Item cannot be null");
        }
        if(user == null) {
            throw new BillException("User cannot be null");
        }
        if(itemsOrdered.size() > 30) {
            throw new BillException("There cannot be more than 30 items");
        }

        double cheapestProcessor = Double.MAX_VALUE;
        double cheapestMouse = Double.MAX_VALUE;
        double cheapestKeyboard = Double.MAX_VALUE;

        // Itera su tutti gli item dell'ordine
        for(EItem item : itemsOrdered) {
            switch(item.getItemType()) {
                // Se e' un processore, trova il meno caro e incrementa countProc
                case PROCESSOR:
                    countProc++;
                    if (item.getPrice() < cheapestProcessor) {
                        cheapestProcessor = item.getPrice();
                    }
                    break;
                // Se e' un mouse, trova il meno caro e incrementa countMouse
                case MOUSE:
                    countMouse++;
                    if(item.getPrice() < cheapestMouse) {
                        cheapestMouse = item.getPrice();
                    }
                    break;
                // Se e' una tastiera, trova la meno cara e incrementa countKey
                case KEYBOARD:
                    countKey++;
                    if(item.getPrice() < cheapestKeyboard) {
                        cheapestKeyboard = item.getPrice();
                    }
                    break;
                default:
                    break;
            }
            // Somma parziale
            tot += item.getPrice();
        }

        // Se ci sono piu' di 5 processori fai uno sconto del 50% sul meno caro
        if(countProc > 5) {
            return tot - cheapestProcessor / 2;
        }
        // Se ci sono piu' di 10 mouse regala il meno caro
        if(countMouse > 10) {
            return tot - cheapestMouse;
        }
        // Se il numero di mouse equivale al numero di tastiere regala il meno caro
        if(countMouse == countKey) {
            return tot - Math.min(cheapestKeyboard, cheapestMouse);
        }
        // Se il totale e' maggiore di 1000 euro fai uno sconto del 10%
        if(tot > 1000.0) {
            return tot - (0.1 * tot);
        }
        // Se il totale e' minore di 10 euro aggiungi 2 euro di commissione
        if(tot < 10.0) {
            tot += 2.0;
        }

        return tot;
    }
}