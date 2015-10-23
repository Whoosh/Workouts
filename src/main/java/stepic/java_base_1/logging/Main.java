package stepic.java_base_1.logging;


import java.util.logging.*;

public class Main {

    public static void main(String[] args) {
        Logger ok = Logger.getLogger("org.stepic.java.logging.ClassA");
        ok.setLevel(Level.WARNING);
        ok.log(Level.SEVERE,"gg {0}",ok.getName());
    }

    private static void configureLogging() {
        Logger parentLogger = Logger.getLogger("org.stepic.java");
        Logger classALogger = Logger.getLogger("org.stepic.java.logging.ClassA");
        Logger classBLogger = Logger.getLogger("org.stepic.java.logging.ClassB");
        classALogger.setLevel(Level.ALL);
        classBLogger.setLevel(Level.WARNING);
        parentLogger.setUseParentHandlers(false);
        ConsoleHandler consoleXMLHandler = new ConsoleHandler();
        consoleXMLHandler.setLevel(Level.ALL);
        consoleXMLHandler.setFormatter(new XMLFormatter());
        parentLogger.addHandler(consoleXMLHandler);
    }
}
