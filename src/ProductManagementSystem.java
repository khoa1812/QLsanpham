import java.util.Scanner;

public class ProductManagementSystem {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("0. Thoát");

            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    int page = 1;
                    int pageSize = 5;
                    while (true) {
                        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
                        productManager.displayProducts((page - 1) * pageSize, page * pageSize);
                        System.out.println("===============================");
                        System.out.println("1. Xem trang tiếp theo");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("Nhập lựa chọn: ");
                        int option = scanner.nextInt();
                        if (option == 1) {
                            page++;
                        } else if (option == 0) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("===== THÊM MỚI SẢN PHẨM =====");
                    System.out.print("Nhập mã sản phẩm: ");
                    String newProductCode = scanner.nextLine();
                    System.out.print("Nhập tên sản phẩm: ");
                    String newProductName = scanner.nextLine();
                    System.out.print("Nhập giá sản phẩm: ");
                    double newProductPrice = scanner.nextDouble();
                    System.out.print("Nhập số lượng sản phẩm: ");
                    int newProductQuantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập mô tả sản phẩm: ");
                    String newProductDescription = scanner.nextLine();

                    Product newProduct = new Product(newProductCode, newProductName,
                            newProductPrice, newProductQuantity,
                            newProductDescription);

                    productManager.addProduct(newProduct);
                    System.out.println("Thêm sản phẩm thành công!");
                    break;
                case 3:
                    System.out.println("===== CẬP NHẬT SẢN PHẨM =====");
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String updateProductCode = scanner.nextLine();
                    Product existingProduct = null;

                    for (Product product : productManager.getProductList()) {
                        if (product.getProductCode().equals(updateProductCode)) {
                            existingProduct = product;
                            break;
                        }
                    }

                    if (existingProduct != null) {
                        System.out.println("Thông tin sản phẩm cần cập nhật:");
                        System.out.println(existingProduct);

                        System.out.print("Nhập mã sản phẩm mới: ");
                        String updatedProductCode = scanner.nextLine();
                        System.out.print("Nhập tên sản phẩm mới: ");
                        String updatedProductName = scanner.nextLine();
                        System.out.print("Nhập giá sản phẩm mới: ");
                        double updatedProductPrice = scanner.nextDouble();
                        System.out.print("Nhập số lượng sản phẩm mới: ");
                        int updatedProductQuantity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập mô tả sản phẩm mới: ");
                        String updatedProductDescription = scanner.nextLine();

                        Product updatedProduct = new Product(updatedProductCode, updatedProductName,
                                updatedProductPrice, updatedProductQuantity,
                                updatedProductDescription);

                        productManager.updateProduct(updateProductCode, updatedProduct);
                        System.out.println("Cập nhật sản phẩm thành công!");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm với mã " + updateProductCode);
                    }
                    break;
                case 4:
                    System.out.println("===== XÓA SẢN PHẨM =====");
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String deleteProductCode = scanner.nextLine();

                    Product productToDelete = null;
                    for (Product product : productManager.getProductList()) {
                        if (product.getProductCode().equals(deleteProductCode)) {
                            productToDelete = product;
                            break;
                        }
                    }

                    if (productToDelete != null) {
                        System.out.println("Thông tin sản phẩm cần xóa:");
                        System.out.println(productToDelete);

                        System.out.print("Xác nhận xóa? (Y/N): ");
                        String confirmation = scanner.nextLine().toUpperCase();

                        if (confirmation.equals("Y")) {
                            productManager.deleteProduct(deleteProductCode);
                            System.out.println("Xóa sản phẩm thành công!");
                        } else {
                            System.out.println("Không xóa sản phẩm.");
                        }
                    } else {
                        System.out.println("Không tìm thấy sản phẩm với mã " + deleteProductCode);
                    }
                    break;
                case 5:
                    System.out.println("===== SẮP XẾP SẢN PHẨM =====");
                    System.out.println("1. Sắp xếp tăng dần theo giá");
                    System.out.println("2. Sắp xếp giảm dần theo giá");
                    System.out.println("3. Quay lại menu chính");
                    System.out.print("Nhập lựa chọn: ");
                    int sortOption = scanner.nextInt();

                    if (sortOption == 1) {
                        productManager.sortProductsByPrice(true);
                        System.out.println("Sắp xếp tăng dần thành công!");
                    } else if (sortOption == 2) {
                        productManager.sortProductsByPrice(false);
                        System.out.println("Sắp xếp giảm dần thành công!");
                    }
                    break;
                case 6:
                    Product mostExpensiveProduct = productManager.findMostExpensiveProduct();
                    if (mostExpensiveProduct != null) {
                        System.out.println("===== SẢN PHẨM GIÁ ĐẮT NHẤT =====");
                        System.out.println(mostExpensiveProduct);
                    } else {
                        System.out.println("Không có sản phẩm nào trong danh sách.");
                    }
                    break;
                case 7:
                    System.out.println("===== ĐỌC TỪ FILE CSV =====");
                    System.out.print("Nhập đường dẫn file CSV (mặc định là 'products.csv'): ");
                    String csvFilePath = scanner.nextLine();
                    if (csvFilePath.isEmpty()) {
                        csvFilePath = "products.csv";
                    }

                    productManager.readProductsFromCSV(csvFilePath);
                    System.out.println("Đọc từ file CSV thành công!");
                case 8:
                    // Ghi vào file CSV
                    System.out.println("===== GHI VÀO FILE CSV =====");
                    System.out.print("Nhập đường dẫn file CSV (mặc định là 'products.csv'): ");
                    String writeCsvFilePath = scanner.nextLine();
                    if (writeCsvFilePath.isEmpty()) {
                        writeCsvFilePath = "products.csv";
                    }

                    productManager.writeProductsToCSV(writeCsvFilePath);
                    System.out.println("Ghi vào file CSV thành công!");

            }
        }
    }
}

