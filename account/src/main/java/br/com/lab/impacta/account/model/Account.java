package br.com.lab.impacta.account.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    private Double balance;

    public boolean debit(Double valueOfDebit){
        if(this.balance < valueOfDebit)
            return false;

        this.setBalance(this.balance - valueOfDebit);
        return true;
    }
}
