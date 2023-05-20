package CameraRentalApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Camera {
    private int id;
    private String brand;
    private String model;
    private double price;
    private boolean available;

    public Camera(int id, String brand, String model, double price, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

class CameraRentalApp {
    private List<Camera> cameras;
    private Wallet wallet;
    private Scanner scanner;

    public CameraRentalApp() {
        cameras = new ArrayList<>();
        wallet = new Wallet(0.0);
        scanner = new Scanner(System.in);

        cameras.add(new Camera(1, "Samsung", "DS123", 500.0, true));
        cameras.add(new Camera(2, "Sony", "HD214", 500.0, true));
        cameras.add(new Camera(3, "Panasonic", "XC", 500.0, true));
        cameras.add(new Camera(4, "Canon", "XLR", 500.0, true));
        cameras.add(new Camera(5, "Fujitsu", "J5", 500.0, true));
        cameras.add(new Camera(6, "Sony", "HD226", 500.0, true));
        cameras.add(new Camera(8, "LG", "L123", 500.0, true));
        cameras.add(new Camera(9, "Canon", "XPL", 500.0, true));
        cameras.add(new Camera(10, "Chroma", "CT", 500.0, true));
        cameras.add(new Camera(13, "Canon", "Digital", 123.0, true));
        cameras.add(new Camera(14, "NIKON", "DSLR-D7500", 500.0, true));
        cameras.add(new Camera(15, "Sony", "DSLR-12", 500.0, true));
        cameras.add(new Camera(19, "SONY", "SONY1234", 123.0, true));
        cameras.add(new Camera(21, "nikon", "2030", 500.0, true));
    }

public void run() {
	System.out.println("+-------------------------------------------+");
	System.out.println("|        WELCOME TO CAMERA RENTAL APP       |");
	System.out.println("+-------------------------------------------+");
    System.out.println("PLEASE LOGIN TO CONTINUE");
    System.out.print("USERNAME: ");
    String username = scanner.nextLine();
    System.out.print("PASSWORD: ");
    String password = scanner.nextLine();

    if (login(username, password)) {
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    handleMyCamera();
                    break;
                case 2:
                    handleRentCamera();
                    break;
                case 3:
                    viewAllCameras();
                    break;
                case 4:
                    handleWallet();
                    break;
                case 5:
                    System.out.println("Thank you for using the Camera Rental App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    } else {
        System.out.println("Login failed. Exiting the Camera Rental App.");
    }
}																								   

private boolean login(String username, String password) {
    return username.equals("admin") && password.equals("admin123");
}

private void displayMainMenu() {
    System.out.println("1. MY CAMERA");
    System.out.println("2. RENT A CAMERA");
    System.out.println("3. VIEW ALL CAMERAS");
    System.out.println("4. MY WALLET");
    System.out.println("5. EXIT");
    System.out.print("Enter your choice: ");
}
    
private void handleMyCamera() {
    boolean backToMain = false;
    while (!backToMain) {
        System.out.println("1. ADD");
        System.out.println("2. REMOVE");
        System.out.println("3. VIEW MY CAMERAS");
        System.out.println("4. GO TO PREVIOUS MENU");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        switch (choice) {
        case 1:
            addCamera();
            break;
        case 2:
            removeCamera();
            break;
        case 3:
            viewMyCameras();
            break;
        case 4:
            backToMain = true;
            break;
        default:
            System.out.println("Invalid choice! Please try again.");
        }
    }
}
    

public static Camera getCameraById(List<Camera> cameras, int id) {
	for (Camera camera : cameras) {
		if (camera.getId() == id) {
			return camera;
		}
	}
	return null; 
}

private void addCamera() {
    System.out.print("ENTER THE CAMERA BRAND: ");
    String brand = scanner.nextLine();
    System.out.print("ENTER THE MODEL: ");
    String model = scanner.nextLine();
    System.out.print("ENTER THE PER DAY PRICE (INR): ");
    double price = scanner.nextDouble();
    scanner.nextLine(); 

    int newId;
    if (cameras.isEmpty()) {
        newId = 1;
    } else {
        Camera lastCamera = cameras.get(cameras.size() - 1);
        newId = lastCamera.getId() + 1; 
    }

    Camera camera = new Camera(newId, brand, model, price, true);
    cameras.add(camera);

    System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST.");
}


private void removeCamera() {
    viewMyCameras();

    System.out.print("ENTER THE CAMERA ID TO REMOVE: ");
    int cameraId = scanner.nextInt();
    scanner.nextLine(); 

    boolean cameraRemoved = false;
    for (Camera camera : cameras) {
        if (camera.getId() == cameraId) {
            cameras.remove(camera);
            cameraRemoved = true;
            break;
        }
    }

    if (cameraRemoved) {
        System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST.");
    } else {
        System.out.println("Invalid camera ID or camera is not available.");
    }
}

private void viewMyCameras() {
	System.out.println("================================================================");
    System.out.println("CAMERA ID | BRAND | MODEL | PRICE (PER DAY) | STATUS");
    System.out.println("================================================================");
    for (Camera camera : cameras) {
        System.out.printf("%-10d%-10s%-12s%-17.2f%s%n",
                camera.getId(), camera.getBrand(), camera.getModel(), camera.getPrice(),
                (camera.isAvailable() ? "Available" : "Rented"));
    }
    
    System.out.println("================================================================");

}

private void handleRentCamera() {
    viewAllCameras();

    System.out.print("ENTER THE CAMERA ID YOU WANT TO RENT: ");
    int cameraId = scanner.nextInt();
    scanner.nextLine(); 

    Camera selectedCamera = null;
    for (Camera camera : cameras) {
        if (camera.getId() == cameraId && camera.isAvailable()) {
            selectedCamera = camera;
            break;
        }
    }

    if (selectedCamera != null) {
        double price = selectedCamera.getPrice();
        if (wallet.getBalance() >= price) {
            wallet.withdraw(price);
            selectedCamera.setAvailable(false);
            System.out.printf("YOUR TRANSACTION FOR CAMERA - %s %s with rent INR %.2f HAS SUCCESSFULLY COMPLETED.%n",
                    selectedCamera.getBrand(), selectedCamera.getModel(), price);
        } else {
            System.out.println("ERROR: TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE. " +
                    "PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET.");
        }
    } else {
        System.out.println("Invalid camera ID or camera is not available.");
    }
}

private void viewAllCameras() {
    System.out.println("================================================================");
    System.out.println("CAMERA ID | BRAND | MODEL | PRICE (PER DAY) | STATUS");
    System.out.println("================================================================");
    for (Camera camera : cameras) {
        System.out.printf("%-10d%-10s%-12s%-17.2f%s%n",
                camera.getId(), camera.getBrand(), camera.getModel(), camera.getPrice(),
                (camera.isAvailable() ? "Available" : "Rented"));
    }
    
    System.out.println("================================================================");
}

private void handleWallet() {
    System.out.println("YOUR CURRENT WALLET BALANCE IS: INR " + wallet.getBalance());

    System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET? (1.YES 2.NO): ");
    int choice = scanner.nextInt();
    scanner.nextLine(); 

    if (choice == 1) {
        System.out.print("ENTER THE AMOUNT (INR): ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        wallet.deposit(amount);
        System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR " + wallet.getBalance());
    }
}
}




public class CameraRentalApplication {
    public static void main(String[] args) {
        CameraRentalApp app = new CameraRentalApp();
        app.run();
    }
}


