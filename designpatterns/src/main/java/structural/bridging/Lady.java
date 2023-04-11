package structural.bridging;

public class Lady extends Person{

    public Lady() {
        setType("女人");
    }

    @Override
    void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
