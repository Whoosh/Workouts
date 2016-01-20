package tazk_3.servlets;

import tazk_3.profiles.UserProfile;
import tazk_3.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by whoosh on 12/21/15.
 */
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        if (accountService.getUserByLogin(login) == null) {
            accountService.addNewUser(new UserProfile(login,pass));
            resp.getWriter().println("Registered");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            resp.getWriter().println("Authorized");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
