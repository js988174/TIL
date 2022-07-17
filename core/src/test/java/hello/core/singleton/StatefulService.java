package hello.core.singleton;

public class StatefulService {

    private int price;

    public int order(String name, int price) {
//        this.price = price;
        return price;
    }

    public int getPrice() {
        return price;
    }
}
