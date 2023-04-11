package structural.bridging;

public class Trouser extends Clothing{
    @Override
    void personDressCloth(Person person) {
        System.out.println(person.getType() + "穿裤子");
    }
}
