import java.util.Date;
import java.util.HashMap;

public class Transaction {
    private String transactionID;
    private String senderID;
    private String receiverID;
    private double amount;
    private Date date;

    private static HashMap<String, User> userDatabase;

    public Transaction(String transactionID, String senderID, String receiverID, double amount) {
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
        this.date = new Date();
    }

    public void processTransaction() {
        User sender = userDatabase.get(senderID);
        User receiver = userDatabase.get(receiverID);

        if (sender != null && receiver != null && sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            System.out.println("Transaksi Diproses dengan Id: " + transactionID);
        } else {
            System.out.println("Transaksi Gagal. Saldo kurang atau Id Penerima tidak ada.");
        }
    }

    public void viewTransactionDetails() {
        // Implementasi detail transaksi
        System.out.println("ID Transaksi: " + transactionID);
        System.out.println("ID Pengirim: " + senderID);
        System.out.println("ID Penerima: " + receiverID);
        System.out.println("Jumlah: " + amount);
        System.out.println("Tanggal: " + date);
    }

    // Setter untuk menetapkan userDatabase
    public static void setUserDatabase(HashMap<String, User> userDatabase) {
        Transaction.userDatabase = userDatabase;
    }



    // Getter methods
    public String getTransactionID() {
        return transactionID;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
