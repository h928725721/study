package creation.builder;

public class ManBuilder implements PersonBuilder{

    private final Person person;

    public ManBuilder() {
        this.person = new Man();
    }

    @Override
    public void buildHead() {
        person.setHead("构建男人的头");
    }

    @Override
    public void buildBody() {
        person.setBody("构建男人的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("构建男人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
