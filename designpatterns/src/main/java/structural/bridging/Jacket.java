package structural.bridging;

public class Jacket extends Clothing{
    @Override
    void personDressCloth(Person person) {
        System.out.println(person.getType() + "穿马甲");
    }
}
