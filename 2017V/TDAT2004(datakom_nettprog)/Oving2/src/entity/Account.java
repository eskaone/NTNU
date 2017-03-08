package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by wizard man on 06.03.2017.
 */
@Entity
@NamedQuery(name="getAmountOfAccounts", query="SELECT COUNT(a) from Account a")

public class Account implements Serializable {

    @Id
    private long accNr;
    private double balance;
    private String accOwner;

    @Version
    private int version;

    public Account() {}

    public Account(long accNr, double balance, String accOwner) {
        this.accNr = accNr;
        this.balance = balance;
        this.accOwner = accOwner;
    }

    public long getAccNr() {
        return accNr;
    }

    public void setAccNr(long accNr) {
        this.accNr = accNr;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccOwner() {
        return accOwner;
    }

    public void setAccOwner(String accOwner) {
        this.accOwner = accOwner;
    }

    public double drawAmount(double amt) {
        setBalance(getBalance() - amt);
        return getBalance();
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNr=" + accNr +
                ", balance=" + balance +
                ", accOwner='" + accOwner + '\'' +
                '}';
    }
}
