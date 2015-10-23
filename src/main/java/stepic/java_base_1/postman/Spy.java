package stepic.java_base_1.postman;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Spy implements MailService {

    Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            MailMessage mailMessage = (MailMessage) mail;
            if (mailMessage.getFrom().equals("Austin Powers") || mailMessage.getTo().equals("Austin Powers")) {
                logger.log(Level.WARNING,"Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[] {mail.getFrom(), mail.getTo(), mailMessage.getMessage()});
            } else {
                logger.log(Level.INFO,"Usual correspondence: from {0} to {1}",new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
            }
        }
        return mail;
    }
}
