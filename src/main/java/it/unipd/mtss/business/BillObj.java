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

        if(itemsOrdered == null) {
            throw new BillException("Items Ordered List cannot be null");
        }
        if(itemsOrdered.contains(null)) {
            throw new BillException("Item cannot be null");
        }
        if(user == null) {
            throw new BillException("User cannot be null");
        }

        for(EItem item : itemsOrdered) {
            tot += item.getPrice();
        }
        return tot;
    }
}