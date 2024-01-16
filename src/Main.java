import java.util.HashMap;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        HashMap<String, User> userDatabase = new HashMap<>();
//
//        User user1 = User.register("John Doe", "john@example.com", "+1234567890", "password1", userDatabase);
//        User user2 = User.register("Jane Smith", "jane@example.com", "+1987654321", "password2",userDatabase);
//
//        Wallet.setUserDatabase(userDatabase);
//        Transaction.setUserDatabase(userDatabase);
//        RewardProgram.setUserDatabase(userDatabase);
//
//        System.out.println(user1.getUserID());
//
//        User.login(user1.getUserID(), "password1");
//
//        System.out.println(user1.viewBalance());
//        System.out.println(user2.viewBalance());
//
//        Wallet wallet1 = new Wallet("BCA111", user1.getUserID() , "BCA" );
//        Wallet wallet2 = new Wallet("BCA222", user2.getUserID() , "BCA" );
//
//        wallet1.addFunds(1000000);
//        wallet2.addFunds(1000000);
//
//        System.out.println(user1.viewBalance());
//        System.out.println(user2.viewBalance());
//
//        user1.transferFunds(user2.getUserID(), 500000);
//
//        System.out.println(user1.viewBalance());
//        System.out.println(user2.viewBalance());
//
//
//        RewardProgram rewardProgram1 = new RewardProgram();
//        rewardProgram1.earnPoints(user1.getUserID(), 200);
//        rewardProgram1.redeemPoints(user1.getUserID());
//
//        System.out.println("User 1 Reward Points: " + rewardProgram1.getRewardPoints());
//        System.out.println("User 1 Available Offers: " + rewardProgram1.getAvailableOffers());
//
//
//    }
//}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> userDatabase = new HashMap<>();

        Wallet.setUserDatabase(userDatabase);
        Transaction.setUserDatabase(userDatabase);
        RewardProgram.setUserDatabase(userDatabase);

        //inisialiasasi beberapa user dan wallet
        User user1 = User.register("Iqbal", "iqbal@example.com", "+1234567890", "password1", userDatabase);
        User user2 = User.register("Farid", "Farid@example.com", "+1987654321", "password2",userDatabase);

        //Main program
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loginUser(scanner, userDatabase);
                    break;
                case 2:
                    registerUser(scanner, userDatabase);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan Invalid");
            }
        }
    }

    private static void loginUser(Scanner scanner, HashMap<String, User> userDatabase) {
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = User.login(userID, password);

        if (user != null) {
            enterMainMenu(scanner, user);
        } else {
            System.out.println("Login kredensial bermasalah, coba lagi");
        }
    }

    private static void registerUser(Scanner scanner, HashMap<String, User> userDatabase) {
        System.out.print("Masukan nama: ");
        String name = scanner.nextLine();
        System.out.print("Masukan email: ");
        String email = scanner.nextLine();
        System.out.print("Masukan No Telepon: ");
        String phone = scanner.nextLine();
        System.out.print("Masukan password: ");
        String password = scanner.nextLine();

        User user = User.register(name, email, phone, password, userDatabase);
        System.out.println("registrasi Berhasil. User ID kamu: " + user.getUserID());
    }

    private static void enterMainMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Hubungkan Dompet(bank)");
            System.out.println("3. Add Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Withdraw Funds");
            System.out.println("6. Dapatkan Point");
            System.out.println("7. Tukarkan Point");
            System.out.println("8. Logout");
            System.out.print("Pilih salah satu opsi: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Saldo anda Sekarang: " + user.viewBalance());
                    break;
                case 2:
                    System.out.println("Hubungkan ke bank: ");
                    System.out.print("Masukan UserId: ");
                    String Userid = scanner.nextLine();
                    System.out.print("Masukan Nama Bank: ");
                    String LinkedBankAccount = scanner.nextLine();
                    Wallet wallet = new Wallet(LinkedBankAccount + System.currentTimeMillis(), Userid , LinkedBankAccount );
                    System.out.println("walletId Kamu:" + wallet.getWalletID());
                    user.setWallet(wallet);
                    break;
                case 3:
                    if (user.getWallet() != null) {
                        System.out.print("Enter amount to add to your wallet: ");
                        double addAmount = scanner.nextDouble();
                        user.getWallet().addFunds(addAmount);
                    } else {
                        System.out.println("User wallet tidak ditemukan, konekan ke bank terlebih dahulu");
                    }
                    break;
                case 4:
                    System.out.print("Masukan Penerima user ID: ");
                    String recipientID = scanner.nextLine();
                    System.out.print("Jumlah Saldo yg ingin dikirim: ");
                    double transferAmount = scanner.nextDouble();
                    user.transferFunds(recipientID, transferAmount);
                    break;
                case 5:
                    System.out.print("Jumlah Saldo yg ingin ditarik: ");
                    double withdrawAmount = scanner.nextDouble();
                    user.getWallet().withdrawFunds(withdrawAmount);
                    break;
                case 6:
                    if (user.getRewardProgram() == null){
                        RewardProgram rewardProgram = new RewardProgram();
                        user.setRewardProgram(rewardProgram);
                    }
                    System.out.print("Point yang ingin didapatkan: ");
                    int pointsEarned = scanner.nextInt();
                    user.getRewardProgram().earnPoints(user.getUserID(), pointsEarned);
                    System.out.print("Point kamu:  " + user.getRewardProgram().getRewardPoints());
                    break;
                case 7:
                    if (user.getRewardProgram() == null){
                        RewardProgram rewardProgram = new RewardProgram();
                        user.setRewardProgram(rewardProgram);
                    }
                    user.getRewardProgram().redeemPoints(user.getUserID());
                    break;
                case 8:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}