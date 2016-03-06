package com.raghavpro.bookhive.models;

/**
 * Represents a user
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class User {

    private int userId;

    /**
     * User's first name
     */
    private String firstName;

    /**
     * User's last name
     */
    private String lastName;

    /**
     * User's username, which is used to log in.
     */
    private String userName;

    /**
     * Password (encrypted)
     */
    private String password;

    /**
     * User's email
     */
    private String email;

    /**
     * User's address
     */
    private String address;

    /**
     * Gender
     */
    private String gender;

    /**
     * Date of account creation
     */
    private String date;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Concatenates first name and last name to get the full name of the user.
     *
     * @return Full name of the user
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
