////////////////////////////////////////////////////////////////////
// Alessio Turetta 2008069
// Mattia Piva 2008065
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem{
    public enum category {PROCESSOR, MOTHERBOARD, MOUSE, KEYBOARD}

    private category itemType;
    private String name;
    private double price;

    public EItem(category itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        if(price >= 0.0D) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public category getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
