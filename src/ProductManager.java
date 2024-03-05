import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProductManager {
    private List<Product> productList;

    public ProductManager() {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void displayProducts(int startIndex, int endIndex) {
        for (int i = startIndex; i < Math.min(endIndex, productList.size()); i++) {
            System.out.println(productList.get(i));
        }
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void updateProduct(String productCode, Product newProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductCode().equals(productCode)) {
                productList.set(i, newProduct);
                return;
            }
        }
    }

    public void deleteProduct(String productCode) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductCode().equals(productCode)) {
                iterator.remove();
                return;
            }
        }
    }

    public void sortProductsByPrice(boolean ascending) {
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        if (!ascending) {
            Collections.reverse(productList);
        }
    }

    public Product findMostExpensiveProduct() {
        return productList.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public void readProductsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            productList.clear(); // Xóa danh sách hiện tại trước khi đọc từ file mới

            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                if (tokenizer.countTokens() == 5) {
                    String productCode = tokenizer.nextToken().trim();
                    String productName = tokenizer.nextToken().trim();
                    double productPrice = Double.parseDouble(tokenizer.nextToken().trim());
                    int productQuantity = Integer.parseInt(tokenizer.nextToken().trim());
                    String productDescription = tokenizer.nextToken().trim();

                    Product product = new Product(productCode, productName, productPrice, productQuantity, productDescription);
                    productList.add(product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Đã xảy ra lỗi khi đọc từ file CSV: " + e.getMessage());
        }
    }

    public void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            System.out.println("===== DANH SÁCH SẢN PHẨM =====");
            for (Product product : productList) {
                System.out.println(product);
            }
            System.out.println("===============================");
        }
    }

    public void writeProductsToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Mã sản phẩm,Tên sản phẩm,Giá,Số lượng,Mô tả");
            writer.newLine();

            for (Product product : productList) {
                writer.write(String.format("%s,%s,%.2f,%d,%s",
                        product.getProductCode(), product.getProductName(),
                        product.getPrice(), product.getQuantity(),
                        product.getDescription()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
