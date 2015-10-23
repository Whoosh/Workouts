package stepic.java_base_1.final_task;

import java.util.*;
import java.util.function.*;

public class Main {


    public static void main(String[] args) {

        String randomFrom = "A"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "B";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 10;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage("Stephen Hawking", "Christopher Nolan", "Я так и не понял Интерстеллар.");
        List<MailMessage> messages = Arrays.asList(firstMessage, secondMessage, thirdMessage);

        MailService<String> mailService = new MailService<>();
        messages.stream().forEachOrdered(mailService);
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";

        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        MailService<Integer> salaryService = new MailService<>();

        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }


    public static class MailMessage<T> implements Context<String> {

        private String from;
        private String to;
        private String content;
        private Integer salary;

        public MailMessage(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }

        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public String getContent() {
            return content;
        }
    }

    public static interface Context<T> {
        String getTo();

        T getContent();
    }

    public static class Salary implements Context<Integer> {

        private String department;
        private String employer;
        private int salary;

        public Salary(String department, String employer, int salary) {
            this.department = department;
            this.employer = employer;
            this.salary = salary;
        }

        @Override
        public String getTo() {
            return employer;
        }

        @Override
        public Integer getContent() {
            return salary;
        }
    }

    public static class MailService<T> implements Consumer<Context> {

        private Map<String, List<T>> mailBox;

        public MailService() {
            this.mailBox = new HashMap<String, List<T>>() {
                @Override
                public List<T> get(Object key) {
                    if (containsKey(key)) return super.get(key);
                    return Collections.<T>emptyList();
                }
            };
        }

        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }

        @Override
        public void accept(Context mailMessage) {
            if (mailBox.containsKey(mailMessage.getTo())) {
                List<T> messages = mailBox.get(mailMessage.getTo());
                messages.add((T) mailMessage.getContent());
                mailBox.put(mailMessage.getTo(), messages);
            } else {
                List<T> message = new ArrayList<>();
                message.add((T) mailMessage.getContent());
                mailBox.put(mailMessage.getTo(), message);
            }
        }
    }
}
