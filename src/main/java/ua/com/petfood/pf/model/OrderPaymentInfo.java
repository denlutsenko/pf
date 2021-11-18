package ua.com.petfood.pf.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import ua.com.petfood.pf.model.dto.OrderProcessStatus;

@Entity
@Table(name = "pf_order_payment_info")
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

    @Enumerated(EnumType.STRING)
    private OrderProcessStatus orderProcessStatus =  OrderProcessStatus.NEW;


    public OrderPaymentInfo() {
        //constructor
    }

    public OrderProcessStatus getOrderProcessStatus() {
        return orderProcessStatus;
    }

    public void setOrderProcessStatus(final OrderProcessStatus orderProcessStatus) {
        this.orderProcessStatus = orderProcessStatus;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public String getLiqpay_order_id() {
        return liqpay_order_id;
    }

    public void setLiqpay_order_id(final String liqpay_order_id) {
        this.liqpay_order_id = liqpay_order_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(final int payment_id) {
        this.payment_id = payment_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public int getAcq_id() {
        return acq_id;
    }

    public void setAcq_id(final int acq_id) {
        this.acq_id = acq_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(final String paytype) {
        this.paytype = paytype;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(final String public_key) {
        this.public_key = public_key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(final String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getSender_card_mask2() {
        return sender_card_mask2;
    }

    public void setSender_card_mask2(final String sender_card_mask2) {
        this.sender_card_mask2 = sender_card_mask2;
    }

    public String getSender_card_bank() {
        return sender_card_bank;
    }

    public void setSender_card_bank(final String sender_card_bank) {
        this.sender_card_bank = sender_card_bank;
    }

    public String getSender_card_type() {
        return sender_card_type;
    }

    public void setSender_card_type(final String sender_card_type) {
        this.sender_card_type = sender_card_type;
    }

    public int getSender_card_country() {
        return sender_card_country;
    }

    public void setSender_card_country(final int sender_card_country) {
        this.sender_card_country = sender_card_country;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public String getCard_token() {
        return card_token;
    }

    public void setCard_token(final String card_token) {
        this.card_token = card_token;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public BigDecimal getSender_commission() {
        return sender_commission;
    }

    public void setSender_commission(final BigDecimal sender_commission) {
        this.sender_commission = sender_commission;
    }

    public BigDecimal getReceiver_commission() {
        return receiver_commission;
    }

    public void setReceiver_commission(final BigDecimal receiver_commission) {
        this.receiver_commission = receiver_commission;
    }

    public BigDecimal getAgent_commission() {
        return agent_commission;
    }

    public void setAgent_commission(final BigDecimal agent_commission) {
        this.agent_commission = agent_commission;
    }

    public BigDecimal getAmount_debit() {
        return amount_debit;
    }

    public void setAmount_debit(final BigDecimal amount_debit) {
        this.amount_debit = amount_debit;
    }

    public BigDecimal getAmount_credit() {
        return amount_credit;
    }

    public void setAmount_credit(final BigDecimal amount_credit) {
        this.amount_credit = amount_credit;
    }

    public BigDecimal getCommission_debit() {
        return commission_debit;
    }

    public void setCommission_debit(final BigDecimal commission_debit) {
        this.commission_debit = commission_debit;
    }

    public BigDecimal getCommission_credit() {
        return commission_credit;
    }

    public void setCommission_credit(final BigDecimal commission_credit) {
        this.commission_credit = commission_credit;
    }

    public String getCurrency_debit() {
        return currency_debit;
    }

    public void setCurrency_debit(final String currency_debit) {
        this.currency_debit = currency_debit;
    }

    public String getCurrency_credit() {
        return currency_credit;
    }

    public void setCurrency_credit(final String currency_credit) {
        this.currency_credit = currency_credit;
    }

    public BigDecimal getSender_bonus() {
        return sender_bonus;
    }

    public void setSender_bonus(final BigDecimal sender_bonus) {
        this.sender_bonus = sender_bonus;
    }

    public BigDecimal getAmount_bonus() {
        return amount_bonus;
    }

    public void setAmount_bonus(final BigDecimal amount_bonus) {
        this.amount_bonus = amount_bonus;
    }

    public String getBonus_type() {
        return bonus_type;
    }

    public void setBonus_type(final String bonus_type) {
        this.bonus_type = bonus_type;
    }

    public BigDecimal getBonus_procent() {
        return bonus_procent;
    }

    public void setBonus_procent(final BigDecimal bonus_procent) {
        this.bonus_procent = bonus_procent;
    }

    public String getAuthcode_debit() {
        return authcode_debit;
    }

    public void setAuthcode_debit(final String authcode_debit) {
        this.authcode_debit = authcode_debit;
    }

    public String getAuthcode_credit() {
        return authcode_credit;
    }

    public void setAuthcode_credit(final String authcode_credit) {
        this.authcode_credit = authcode_credit;
    }

    public String getRrn_debit() {
        return rrn_debit;
    }

    public void setRrn_debit(final String rrn_debit) {
        this.rrn_debit = rrn_debit;
    }

    public String getRrn_credit() {
        return rrn_credit;
    }

    public void setRrn_credit(final String rrn_credit) {
        this.rrn_credit = rrn_credit;
    }

    public String getMpi_eci() {
        return mpi_eci;
    }

    public void setMpi_eci(final String mpi_eci) {
        this.mpi_eci = mpi_eci;
    }

    public boolean isIs_3ds() {
        return is_3ds;
    }

    public void setIs_3ds(final boolean is_3ds) {
        this.is_3ds = is_3ds;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(final Date create_date) {
        this.create_date = create_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(final Date end_date) {
        this.end_date = end_date;
    }

    public boolean isMoment_part() {
        return moment_part;
    }

    public void setMoment_part(final boolean moment_part) {
        this.moment_part = moment_part;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(final int transaction_id) {
        this.transaction_id = transaction_id;
    }
}
