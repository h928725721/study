package structural.decorate;

import lombok.Setter;

public abstract class Decorator implements Person{

    @Setter
    protected Person person;

    @Override
    public void eat() {
        person.eat();
    }
}
