package by.tms;

public class Product {
    private String type;
    private String productName;
    private Double price;
    private String overview;
    private Boolean atBasket;

    public Product(String type, String productName, Double price, String overview, Boolean atBasket) {
        this.type = type;
        this.productName = productName;
        this.price = price;
        this.overview = overview;
        this.atBasket = atBasket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Boolean getAtBasket() {
        return atBasket;
    }

    public void setAtBasket(Boolean atBasket) {
        this.atBasket = atBasket;
    }
}
