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
        int countProc = 0;

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

        for(EItem item : itemsOrdered) {
            switch(item.getItemType()) {
                case PROCESSOR:
                    countProc++;
                    if (item.getPrice() < cheapestProcessor) {
                        cheapestProcessor = item.getPrice();
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

        return tot;
    }
}