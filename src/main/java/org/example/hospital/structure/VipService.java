package org.example.hospital.structure;

public enum VipService implements IService {
    SEPARATE_ROOM("Provision of a separate room", 250),
    SPECIAL_FOOD("Provision of a special food", 175),
    WALK_IN_SERVICE("Walk-in service", 200),
    ENTERTAINMENT("Providing entertainment", 150);

    private final String title;
    private final double price;

    VipService(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + " (price: " + price + " BYN)";
    }
}
