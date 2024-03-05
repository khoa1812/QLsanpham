public class Product {
    private String productCode;
    private String productName;
    private double price;
    private int quantity;
    private String description;

    public Product(String productCode, String productName, double price, int quantity, String description) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Mã SP: %s - Tên: %s - Giá: %.2f - Số lượng: %d - Mô tả: %s",
                productCode, productName, price, quantity, description);
    }
}
