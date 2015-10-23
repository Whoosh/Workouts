package stepic.java_base_1.postman;

public class Thief implements MailService {

    private int price;
    private int stolen;

    public Thief(int price) {
        this.price = price;
    }

    public int getStolenValue() {
        return stolen;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            MailPackage mailPackage = (MailPackage) mail;
            if (mailPackage.getContent().getPrice() >= price) {
                stolen += mailPackage.getContent().getPrice();
                Package emptyPackage = new Package("stones instead of " + mailPackage.getContent().getContent(), 0);
                return new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), emptyPackage);
            }
        }
        return mail;
    }
}
