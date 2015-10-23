package stepic.java_base_1.postman;

public class UntrustworthyMailWorker implements MailService {

    private MailService[] services;
    private RealMailService realMailService;

    public UntrustworthyMailWorker(MailService[] services) {
        this.services = services;
        realMailService =  new RealMailService();
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService service : services) mail = service.processMail(mail);
        return realMailService.processMail(mail);
    }

    public RealMailService getRealMailService(){
        return realMailService;
    }
}
