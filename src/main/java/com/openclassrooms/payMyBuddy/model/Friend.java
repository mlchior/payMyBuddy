package com.openclassrooms.payMyBuddy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "id_user")
        private User user;
        @JoinColumn(name ="id_user_friend")
        @ManyToOne
        private User friend;


    public Friend(User friend) {
        this.friend = friend;
    }

    public Friend() {

    }

         public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setFriend(User friend) {
            this.friend = friend;
        }
        public User getFriend() {
            return friend;
        }
}
