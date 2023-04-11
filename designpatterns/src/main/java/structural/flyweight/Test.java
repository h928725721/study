package structural.flyweight;

public class Test {

    public static void main(String[] args) {
        ITicket ticket = TicketFactory.getTicketInfo("深圳", "广州");
        ticket.setSeat("硬座");
        ticket.info();
        //即使外部结构变了，依然可以使用缓存，达到了共享内部状态的目的
        ITicket ticket2 = TicketFactory.getTicketInfo("深圳", "广州");
        ticket.setSeat("硬卧");
        ticket2.info();
        ITicket ticket3 = TicketFactory.getTicketInfo("深圳", "北京");
        ticket3.setSeat("硬座");
        ticket3.info();
    }
}
