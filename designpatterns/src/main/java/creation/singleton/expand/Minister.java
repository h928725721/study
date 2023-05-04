package creation.singleton.expand;

public class Minister {

    public static void main(String[] args) {
        int ministerNum = 5;
        for (int i = 0; i < ministerNum; i++) {
            Emperor e = Emperor.getInstance();
            System.out.println("number " + (i + 1) + " of the Emperor");
            e.say();
        }
    }
}
