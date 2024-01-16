import java.util.HashMap;

public class Wallet {
    private String walletID;
    private String linkedUserID;
    private String linkedBankAccount;
    private double balance;

    private static HashMap<String, User> userDatabase;

    public Wallet(String walletID, String linkedUserID, String linkedBankAccount) {
        this.walletID = walletID;
        this.linkedUserID = linkedUserID;
        this.linkedBankAccount = linkedBankAccount;
        this.balance = 0;

    }

    public void addFunds(double amount) {
        User user = userDatabase.get(linkedUserID);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            this.balance += amount;
            System.out.println("Menambahkan " + amount + " saldo. Saldo sekarang: " + this.balance);
        } else {
            System.out.println("User dengan Id " + linkedUserID + " tidak ditemukan.");
        }
    }

    public void withdrawFunds(double amount) {
        User user = userDatabase.get(linkedUserID);
        if (user != null && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            this.balance -= amount;
            System.out.println("Penarikan " + amount + " saldo. Saldo Sekarang: " + this.balance);
            System.out.println("Kode penarikan: Withdraw" + System.currentTimeMillis());
        } else {
            System.out.println("Saldo tidak mencukupi atau pengguna tidak ditemukan.");
        }
    }

    // Setter untuk menetapkan userDatabase
    public static void setUserDatabase(HashMap<String, User> userDatabase) {
        Wallet.userDatabase = userDatabase;
    }

    // Getter methods
    public String getWalletID() {
        return walletID;
    }

    public String getLinkedUserID() {
        return linkedUserID;
    }

    public String getLinkedBankAccount() {
        return linkedBankAccount;
    }

    public double getBalance() {
        return balance;
    }

}
