package by.tms;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorize")
public class AuthorizeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String login = req.getParameter("login");
        String password = "";
        String method = req.getParameter("method");

        if ("authorize".equals(method))
            password = req.getParameter("password");
        else if ("register".equals(method))
            password = user.generateRandomPassword();

        if (login == null || login.trim().length() == 0) {
            if ("authorize".equals(method))
                resp.sendRedirect("index.jsp?do=auth&error=bad_data");
            else if ("register".equals(method))
                resp.sendRedirect("index.jsp?do=register&error=bad_data");
        } else {
            boolean userValid = false;
            if ("authorize".equals(method))
                userValid = user.authorize(login, password);
            else if ("register".equals(method)) {
                boolean userExists = user.checkUserName(login);
                if (!userExists) {
                    user.register(login, password);
                    try {
                        EmailSender.sendSimpleMessage(login, password);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    userValid = true;
                }
            }
            if (userValid) {
                req.getServletContext().getRequestDispatcher("/shopmain").forward(req, resp);
            } else {
                if ("authorize".equals(method))
                    resp.sendRedirect("index.jsp?do=auth&error=login_fail");
                else if ("register".equals(method))
                    resp.sendRedirect("index.jsp?do=register&error=user_exists");
            }
        }

    }
}
