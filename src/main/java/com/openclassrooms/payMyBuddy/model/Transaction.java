package com.openclassrooms.payMyBuddy.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "id_user")
        private User user;

        @ManyToOne
        @JoinColumn(name = "id_user_reception")
        private User friend;

        private float montant;

        private Date dateTime;

        private float frais;


    // taxe 0,5% sur le montant de la transaction


    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }



        public float getMontant() {
            return montant;
        }

        public void setMontant(float montant) {
            this.montant = montant;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }
    }
