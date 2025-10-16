package com.example.sudoku.model.user;

/**
 * Singleton class representing a user in the Sudoku game.
 */
public class User {
    private static final User INSTANCE = new User();
    private User() {
    }

    /**
     * Gets the singleton instance of the User class.
     * @return
     */
    public static User getInstance() {
        return INSTANCE;
    }
        private String nickname;

    /**
     * Constructor to initialize the User with a nickname.
     * @param nickname
     */
    public User(String nickname) {
            this.nickname = nickname;
        }

    /**
     * Gets the nickname of the user.
     * @return
     */
    public String getNickname () {
            return this.nickname;
        }

    /**
     * Sets the nickname of the user.
     * @param nickname
     */
    public void setNickname (String nickname){
            this.nickname = nickname;
        }
    }

