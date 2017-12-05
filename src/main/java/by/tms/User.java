package by.tms;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Random;

public class User {

    public boolean authorize(String login, String password) {
        boolean isValid = false;
        try {
            XmlDocument xml = new XmlDocument();
            NodeList nodeList = (xml.openXml("users.xml")).getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    if ((xml.getTagValue("login", element)).equals(login) && (xml.getTagValue("password", element)).equals(password))
                        isValid = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public boolean checkUserName(String login) {
        boolean userExists = false;
        try {
            XmlDocument xml = new XmlDocument();
            NodeList nodeList = (xml.openXml("users.xml")).getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    if ((xml.getTagValue("login", element)).equals(login)) {
                        userExists = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userExists;
    }

    public void register(String login, String password) {
        XmlDocument xml = new XmlDocument();
        Document document = xml.openXml("users.xml");
        Element users = (Element) document.getElementsByTagName("users").item(0);
        Element user = document.createElement("user");
        user.appendChild(xml.getTagElements(document, user, "login", login));
        user.appendChild(xml.getTagElements(document, user, "password", password));
        users.appendChild(user);
        xml.transformXml(document);
    }

    public String generateRandomPassword() {
        char[] password = new char[8];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        Random random = new Random();
        for (int passwordLengthCounter = 0; passwordLengthCounter < 8; passwordLengthCounter++) {
            if (random.nextInt(2) == 0) {
                int position = random.nextInt(26);
                alphabet.getChars(position, position + 1, password, passwordLengthCounter);
            } else {
                int position = random.nextInt(10);
                numbers.getChars(position, position + 1, password, passwordLengthCounter);
            }
        }
        return String.valueOf(password);
    }
}
