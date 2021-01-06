package com.helpers;

public class RegexHelper {

    /**
     * FOR USER
     */
    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; // Autorise chiffres, lettres min et maj,
        // tiret underscore
        // et court et point, et rend obligatoire @ ainsi que
        // le point + domaine
        return email.matches(regex);
    }

    public static boolean isUsernameValid(String username) {
        String regex = "^[\\w-]{2,}$";  // Minimum 2 caractères autorisant lettres maj, lettres min,
        // chiffres, tiret underscore et tiret court
        return username.matches(regex);
    }

    public static boolean isPasswordValid(String pass) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";  // Minimum 8 caractères avec au minimum
        // 1 lettre maj, 1 lettre min et un chiffre
        return pass.matches(regex);
    }


    /**
     * FOR CHANNEL
     */
    public static boolean isChannelNameValid(String channelName) {
        String regex = "^[\\w-]{2,20}$";
        return channelName.matches(regex);
    }
}
