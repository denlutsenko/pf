package ua.com.petfood.pf.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pf_order_payment_info")
@Getter
@Setter
public class OrderPaymentInfo extends PersistentEntity<Long> {
    private String order_id;
    @JsonIgnore
    private String liqpay_order_id;
    private String action;
    @JsonIgnore
    private int payment_id;
    @JsonIgnore
    private int version;
    @JsonIgnore
    private int acq_id;
    private String status;
    private String type;
    private String paytype;
    @JsonIgnore
    private String public_key;
    private String description;
    @JsonIgnore
    private String sender_phone;
    @JsonIgnore
    private String sender_card_mask2;
    @JsonIgnore
    private String sender_card_bank;
    @JsonIgnore
    private String sender_card_type;
    @JsonIgnore
    private int sender_card_country;
    @JsonIgnore
    private String ip;
    @JsonIgnore
    private String card_token;

    private String info;
    @JsonIgnore
    private String language;
    private BigDecimal amount;
    private String currency;
    @JsonIgnore
    private BigDecimal sender_commission;
    @JsonIgnore
    private BigDecimal receiver_commission;
    @JsonIgnore
    private BigDecimal agent_commission;
    @JsonIgnore
    private BigDecimal amount_debit;
    @JsonIgnore
    private BigDecimal amount_credit;
    @JsonIgnore
    private BigDecimal commission_debit;
    @JsonIgnore
    private BigDecimal commission_credit;
    @JsonIgnore
    private String currency_debit;
    @JsonIgnore
    private String currency_credit;
    @JsonIgnore
    private BigDecimal sender_bonus;
    @JsonIgnore
    private BigDecimal amount_bonus;
    @JsonIgnore
    private String bonus_type;
    @JsonIgnore
    private BigDecimal bonus_procent;
    @JsonIgnore
    private String authcode_debit;
    @JsonIgnore
    private String authcode_credit;
    @JsonIgnore
    private String rrn_debit;
    @JsonIgnore
    private String rrn_credit;
    @JsonIgnore
    private String mpi_eci;
    @JsonIgnore
    private boolean is_3ds;

    private Date create_date;
    private Date end_date;
    @JsonIgnore
    private boolean moment_part;
    @JsonIgnore
    private int transaction_id;

    public OrderPaymentInfo() {
        //constructor
    }
}



