package structural.bridging;

import lombok.Data;

@Data
public abstract class Person {

    Clothing clothing;

    private String type;


    abstract void dress();

}
