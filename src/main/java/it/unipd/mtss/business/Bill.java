////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;
}
