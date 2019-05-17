package com.sobart.partstock.domain;

import javax.persistence.*;

@Entity
public class Part {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private boolean need;

    private long amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Part() {
    }

    public Part(String name, boolean need, long amount, User user) {
        this.name = name;
        this.need = need;
        this.amount = amount;
        this.owner = user;
    }

    public String getOwnerName(){
        return owner != null ? owner.getUsername() : "none";
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
