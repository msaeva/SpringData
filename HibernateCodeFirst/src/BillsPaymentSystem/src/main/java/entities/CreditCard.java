package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail{
    @Column(length = 30)
    private String cardType;

    @Column(name = "expiration_month")
    private byte expirationMonth;

    @Column(name = "expiration_year")
    private short expirationYear;

}
