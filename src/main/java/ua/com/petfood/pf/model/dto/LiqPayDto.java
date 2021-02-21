package ua.com.petfood.pf.model.dto;

import java.util.Date;

public class LiqPayDto {

    private String action;
    private String version;
    private String amount;
    private String phone;
    private String currency;
    private String description;
    private String order_id;
    private String subscribe;
    private String  subscribe_date_start;
    private String subscribe_periodicity;
    private String card;
    private String card_exp_month;
    private String card_exp_year;
    private String card_cvv;
    private String server_url;
    private String result_url;

    public LiqPayDto() {

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe_date_start() {
        return subscribe_date_start;
    }

    public void setSubscribe_date_start(String subscribe_date_start) {
        this.subscribe_date_start = subscribe_date_start;
    }

    public String getSubscribe_periodicity() {
        return subscribe_periodicity;
    }

    public void setSubscribe_periodicity(String subscribe_periodicity) {
        this.subscribe_periodicity = subscribe_periodicity;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCard_exp_month() {
        return card_exp_month;
    }

    public void setCard_exp_month(String card_exp_month) {
        this.card_exp_month = card_exp_month;
    }

    public String getCard_exp_year() {
        return card_exp_year;
    }

    public void setCard_exp_year(String card_exp_year) {
        this.card_exp_year = card_exp_year;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv) {
        this.card_cvv = card_cvv;
    }

    public String getServer_url() {
        return server_url;
    }

    public void setServer_url(String server_url) {
        this.server_url = server_url;
    }

    public String getResult_url() {
        return result_url;
    }

    public void setResult_url(String result_url) {
        this.result_url = result_url;
    }
}
