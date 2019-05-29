package com.sobart.partstock.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "You need to input name")
    private String name;

    @NotNull(message = "You need to input amount")
    private Long amount;

    private boolean need;

    @NotNull(message = "You need to input price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    private String filename;

    public Part() {
    }

    public Part(String name, boolean need, long amount, User user) {
        this.name = name;
        this.need = need;
        this.amount = amount;
        this.owner = user;
    }

    public String getOwnerName() {
        return owner != null ? owner.getUsername() : "none";
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Double getPrice() {
        return price.doubleValue();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
