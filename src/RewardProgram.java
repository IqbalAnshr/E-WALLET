import java.util.HashMap;

public class RewardProgram {
    private int rewardPoints;
    private String availableOffers;

    private static HashMap<String, User> userDatabase;

    public RewardProgram() {
        this.rewardPoints = 0;
        this.availableOffers = "Diskon Spesial";
    }

    public void earnPoints(String userID, int pointsEarned) {
        User user = userDatabase.get(userID);
        if (user != null) {
            user.setBalance(user.getBalance() + pointsEarned);
            this.rewardPoints += pointsEarned;
            System.out.println("Mendapatkan " + pointsEarned + " points.");
        } else {
            System.out.println("User dengan ID " + userID + " Tidak ditemukan.");
        }
    }

    public void redeemPoints(String userID) {
        User user = userDatabase.get(userID);
        if (user != null && rewardPoints >= 100) {
            rewardPoints -= 100;
            System.out.println("Menukarkan 100 poin untuk penawaran khusus.");
        } else {
            System.out.println("Poin tidak cukup untuk ditukarkan atau pengguna tidak ditemukan.");
        }
    }


    public static void setUserDatabase(HashMap<String, User> userDatabase) {
        RewardProgram.userDatabase = userDatabase;
    }


    public int getRewardPoints() {
        return rewardPoints;
    }

    public String getAvailableOffers() {
        return availableOffers;
    }

    public void setAvailableOffers(String availableOffers) {
        this.availableOffers = availableOffers;
    }
}
