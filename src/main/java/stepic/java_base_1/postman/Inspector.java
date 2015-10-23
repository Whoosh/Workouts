package stepic.java_base_1.postman;


public class Inspector implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            MailPackage mailPackage = (MailPackage) mail;
            if (mailPackage.getContent().getContent().contains("weapons") ||
                    mailPackage.getContent().getContent().contains("banned substance")) {
                throw new IllegalPackageException();
            }
            if (mailPackage.getContent().getContent().contains("stones")) {
                throw new StolenPackageException();
            }
        }
        return mail;
    }

    public static class IllegalPackageException extends RuntimeException {

    }

    public static class StolenPackageException extends RuntimeException {


    }
}



