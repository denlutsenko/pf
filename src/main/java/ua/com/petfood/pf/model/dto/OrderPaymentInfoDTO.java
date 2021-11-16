package ua.com.petfood.pf.model.dto;

import java.math.BigDecimal;
import java.util.Date;

// import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderPaymentInfoDTO {
//    @JsonProperty("order_id")
    private String order_id;

//    @JsonProperty("liqpay_order_id")
    private String liqpay_order_id;

//    @JsonProperty("action")
    private String action;

//    @JsonProperty("payment_id")
    private int payment_id;

//    @JsonProperty("version")
    private int version;

//    @JsonProperty("acq_id")
    private int acq_id;

//    @JsonProperty("status")
    private String status;

//    @JsonProperty("type")
    private String type;

//    @JsonProperty("paytype")
    private String paytype;

//    @JsonProperty("public_key")
    private String public_key;

//    @JsonProperty("description")
    private String description;

//    @JsonProperty("sender_phone")
    private String sender_phone;

//    @JsonProperty("sender_card_mask2")
    private String sender_card_mask2;

//    @JsonProperty("sender_card_bank")
    private String sender_card_bank;

//    @JsonProperty("sender_card_bank")
    private String sender_card_type;

//    @JsonProperty("sender_card_country")
    private int sender_card_country;

//    @JsonProperty("ip")
    private String ip;

//    @JsonProperty("card_token")
    private String card_token;

//    @JsonProperty("info")
    private String info;

//    @JsonProperty("language")
    private String language;

//    @JsonProperty("amount")
    private BigDecimal amount;

//    @JsonProperty("currency")
    private String currency;

//    @JsonProperty("sender_commission")
    private BigDecimal sender_commission;

//    @JsonProperty("receiver_commission")
    private BigDecimal receiver_commission;

//    @JsonProperty("agent_commission")
    private BigDecimal agent_commission;

//    @JsonProperty("amount_debit")
    private BigDecimal amount_debit;

//    @JsonProperty("amount_credit")
    private BigDecimal amount_credit;

//    @JsonProperty("amount_credit")
    private BigDecimal commission_debit;

//    @JsonProperty("commission_credit")
    private BigDecimal commission_credit;

//    @JsonProperty("currency_debit")
    private String currency_debit;

//    @JsonProperty("currency_credit")
    private String currency_credit;

//    @JsonProperty("sender_bonus")
    private BigDecimal sender_bonus;

//    @JsonProperty("amount_bonus")
    private BigDecimal amount_bonus;

//    @JsonProperty("bonus_type")
    private String bonus_type;

//    @JsonProperty("bonus_procent")
    private BigDecimal bonus_procent;

//    @JsonProperty("authcode_debit")
    private String authcode_debit;

//    @JsonProperty("authcode_credit")
    private String authcode_credit;

//    @JsonProperty("rrn_debit")
    private String rrn_debit;

//    @JsonProperty("rrn_credit")
    private String rrn_credit;

//    @JsonProperty("mpi_eci")
    private String mpi_eci;

//    @JsonProperty("is_3ds")
    private boolean is_3ds;

//    @JsonProperty("create_date")
    private Date create_date;

//    @JsonProperty("end_date")
    private Date end_date;

//    @JsonProperty("moment_part")
    private boolean moment_part;

//    @JsonProperty("transaction_id")
    private int transaction_id;
}
