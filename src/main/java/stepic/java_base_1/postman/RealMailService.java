package stepic.java_base_1.postman;

public class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        return mail;
    }
}