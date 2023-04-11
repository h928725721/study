package creation.prototype;

import lombok.Data;

@Data
public class Prototype implements Cloneable{

    private String name;

    @Override
    protected Prototype clone() {
        try {
            Prototype clone = (Prototype)super.clone();
            clone.setName("cloneBean");
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
