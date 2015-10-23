package stepic.java_base_1.final_task.edited;

import java.util.*;
import java.util.function.Consumer;

public class Main {


    public static void main(String[] args) {

        String randomFrom = "A"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "B";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 10;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        MailMessage<String> firstMessage = new <String>MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage<String> secondMessage = new <String>MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage<String> thirdMessage = new <String>MailMessage("Stephen Hawking", "Christopher Nolan", "Я так и не понял Интерстеллар.");

        List<MailMessage> messages = Arrays.asList(firstMessage, secondMessage, thirdMessage);

        MailService<String,String> mailService = new MailService<>();
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

        MailService<String, Integer> salaryService = new MailService<>();

        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        Map<String, List<Integer>> salaries = salaryService.getMailBox();

        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }


    public static class MailMessage<T> implements Context<T> {

        private String from;
        private String to;
        private T content;

        public MailMessage(String from, String to, T content) {
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
        public T getContent() {
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

    public static class MailService<K, V> implements Consumer<Context> {

        private Map<K, List<V>> mailBox;

        public MailService() {
            this.mailBox = new HashMap<K, List<V>>() {
                @Override
                public List<V> get(Object key) {
                    if (containsKey(key)) return super.get(key);
                    return Collections.<V>emptyList();
                }
            };
        }

        public Map<K, List<V>> getMailBox() {
            return mailBox;
        }

        @Override
        public void accept(Context mailMessage) {
            if (mailBox.containsKey(mailMessage.getTo())) {
                List<V> messages = mailBox.get(mailMessage.getTo());
                messages.add((V) mailMessage.getContent());
                mailBox.put((K) mailMessage.getTo(), messages);
            } else {
                List<V> message = new ArrayList<>();
                message.add((V) mailMessage.getContent());
                mailBox.put((K) mailMessage.getTo(), message);
            }
        }
    }
}
