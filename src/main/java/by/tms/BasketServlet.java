package by.tms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/basketmain")
public class BasketServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> products = (ArrayList)req.getServletContext().getAttribute("products");
        for (Product product: products) {
            product.setAtBasket(false);
        }
        Cookie[] cookies = req.getCookies();
        int productId = 0;
        for (Product product: products) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                int id = -1;
                try {
                    id = Integer.valueOf(c.getName().split("_")[1]);
                }
                catch (Exception e) {
                    id = -1;
                }
                if(productId==id) {
                    products.get(productId).setAtBasket(true);
                }
            }
            productId++;
        }
        req.getServletContext().setAttribute("products", products);
        req.getServletContext().getRequestDispatcher("/basket.jsp").forward(req, resp);
    }
}

