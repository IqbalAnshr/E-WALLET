import java.util.HashMap;

public class User {
    private String userID;
    private String name;
    private String email;
    private String phone;
    private String password;
    private double balance;
    private Wallet wallet;
    private RewardProgram rewardProgram;

    private static HashMap<String, User> userDatabase = new HashMap<>();

    public User(String userID, String name, String email, String phone, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.balance = 0;
        userDatabase.put(userID, this);
    }

    public static User login(String userID, String password) {
        if (userDatabase.containsKey(userID)) {
            User user = userDatabase.get(userID);
            if (user.getPassword().equals(password)) {
                System.out.println("Login Berhasil.");
                return user;
            }
        }
        System.out.println("UserId atau Password salah.");
        return null;
    }

    public static User register(String name, String email, String phone, String password, HashMap<String, User> userDatabase) {
        String userID = generateUserID();
        User newUser = new User(userID, name, email, phone, password);
        userDatabase.put(userID, newUser);
        System.out.println("Registrati Berhasil. user ID Kamu: " + userID);
        return newUser;
    }

    private static String generateUserID() {
        return "User" + System.currentTimeMillis();
    }

    public double viewBalance() {
        return balance;
    }

    private static String generateTransactionID() {
        return "Transaction" + System.currentTimeMillis();
    }

    public void transferFunds(String receiverID, double amount) {
        if (amount <= balance) {
            User receiver = userDatabase.get(receiverID);

            if (receiver != null ) {
                Transaction transaction = new Transaction(generateTransactionID(), this.userID, receiverID, amount);
                transaction.processTransaction();
                transaction.viewTransactionDetails();
            } else {
                System.out.println("Penerima dengan ID " + receiverID + " Tidak Ditemukan.");
            }
        } else {
            System.out.println("Saldo Tidak cukup.");
        }
    }

    // Getter and setter methods
    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public RewardProgram getRewardProgram() {
        return rewardProgram;
    }
    public void setRewardProgram(RewardProgram rewardProgram) {
        this.rewardProgram = rewardProgram;
    }
}
