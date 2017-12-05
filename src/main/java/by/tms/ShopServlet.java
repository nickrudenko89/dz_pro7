package by.tms;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/shopmain")
public class ShopServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.getServletContext().setAttribute("userName", login);
        if(req.getParameter("method")!=null) {
            try {
                XmlDocument xml = new XmlDocument();
                ArrayList<Product> products = new ArrayList<Product>();
                NodeList nodeList = (xml.openXml("products.xml")).getElementsByTagName("product");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodeList.item(i);
                        Product product = new Product(xml.getTagValue("type", element), xml.getTagValue("name", element), Double.valueOf(xml.getTagValue("price", element)), xml.getTagValue("overview", element), false);
                        products.add(product);
                    }
                }
                req.getServletContext().setAttribute("products", products);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        req.getServletContext().getRequestDispatcher("/shop.jsp").forward(req, resp);
    }
}
