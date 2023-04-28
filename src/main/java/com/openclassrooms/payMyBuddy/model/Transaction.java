package com.openclassrooms.payMyBuddy.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_transaction")
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "id_user")
        private User sender;

        @ManyToOne
        @JoinColumn(name = "id_user_reception")
        private User receiver;

        @JoinColumn(name = "amount")
        private float amount;

        private LocalDateTime dateTime;

        private float frais;

        //description
        private String description;



    // taxe 0,5% sur le montant de la transaction

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public float getFrais() {
        return frais;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }



        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    }
