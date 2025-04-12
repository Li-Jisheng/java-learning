//
public class Bill {
    private double amount;
    private String billType;

    public Bill(double amount) {
        this.amount = amount;
        if (this.amount < 0) {
            this.billType = "Расходы";
        }
        else {
            this.billType = "Выход";
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType() {
        if (this.amount < 0) {
            this.billType = "Расходы";
        }
        else {
            this.billType = "Выход";
        }
    }

    public String getType() {
        return billType;
    }

    public void printBill() {
        System.out.println("Bill Amount: " + getAmount());
    }
}
