package com.example.sudoku.model.user;

public class User {
    private static final User INSTANCE = new User();
    private User() {
    }
    public static User getInstance() {
        return INSTANCE;
    }
        private String nickname;
    public User(String nickname) {
            this.nickname = nickname;
        }
        public String getNickname () {
            return this.nickname;
        }
        public void setNickname (String nickname){
            this.nickname = nickname;
        }
    }

