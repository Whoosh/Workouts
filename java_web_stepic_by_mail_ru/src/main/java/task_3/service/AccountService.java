package task_3.service;

import tazk_3.profiles.UserProfile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// TODO or NOT TODO for JDBC
public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final File file;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        file = new File("test.txt");
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));
                while (scanner.hasNextLine()) {
                    String login = scanner.nextLine();
                    String password = scanner.nextLine();
                    loginToProfile.put(login, new UserProfile(login, password));
                }
                scanner.close();
            } else {
                file.createNewFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addNewUser(UserProfile u) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(u.getLogin(), 0, u.getLogin().length());
            writer.newLine();
            writer.write(u.getPass(), 0, u.getPass().length());
            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loginToProfile.put(u.getLogin(), u);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
