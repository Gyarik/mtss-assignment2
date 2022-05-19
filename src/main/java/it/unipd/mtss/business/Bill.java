package it.unipd.mtss.business;

import java.util.List;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;
}
