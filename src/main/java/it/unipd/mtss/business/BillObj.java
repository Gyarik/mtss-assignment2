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

        if(itemsOrdered == null) {
            throw new BillException("Items Ordered List cannot be null");
        }
        if(itemsOrdered.contains(null)) {
            throw new BillException("Item cannot be null");
        }
        if(user == null) {
            throw new BillException("User cannot be null");
        }

        double cheapestProcessor = Double.MAX_VALUE;
        double cheapestMouse = Double.MAX_VALUE;
        double cheapestKeyboard = Double.MAX_VALUE;

        for(EItem item : itemsOrdered) {
            switch(item.getItemType()) {
                case PROCESSOR:
                    countProc++;
                    if (item.getPrice() < cheapestProcessor) {
                        cheapestProcessor = item.getPrice();
                    }
                    break;
                case MOUSE:
                    countMouse++;
                    if(item.getPrice() < cheapestMouse) {
                        cheapestMouse = item.getPrice();
                    }
                    break;
                case KEYBOARD:
                    countKey++;
                    if(item.getPrice() < cheapestKeyboard) {
                        cheapestKeyboard = item.getPrice();
                    }
                    break;
                default:
                    break;
            }
            tot += item.getPrice();
        }

        if(countProc > 5) {
            return tot - cheapestProcessor / 2;
        }
        if(countMouse > 10) {
            return tot - cheapestMouse;
        }
        if(countMouse == countKey) {
            return tot - Math.min(cheapestKeyboard, cheapestMouse);
        }
        if(tot > 1000.0) {
            return tot - (0.1 * tot);
        }

        return tot;
    }
}