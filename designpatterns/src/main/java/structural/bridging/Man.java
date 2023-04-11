package structural.bridging;

public class Man extends Person{

    public Man() {
        setType("男人");
    }

    @Override
    void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
