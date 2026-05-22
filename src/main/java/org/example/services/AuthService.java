package org.example.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Autenticación en memoria (simulación sin base de datos).
 */
public class AuthService {

    private static final AuthService INSTANCE = new AuthService();

    private final Map<String, String> credentials = new HashMap<>();
    private final Map<String, String> displayNames = new HashMap<>();

    private AuthService() {
    }

    public static AuthService getInstance() {
        return INSTANCE;
    }

    public enum LoginResult {
        SUCCESS,
        USER_NOT_FOUND,
        WRONG_PASSWORD,
        EMPTY_FIELDS
    }

    public enum RegisterResult {
        SUCCESS,
        EMAIL_EXISTS,
        PASSWORD_MISMATCH,
        EMPTY_FIELDS
    }

    public LoginResult login(String emailOrUser, String password) {
        String key = normalize(emailOrUser);

        if (key.isEmpty() || password == null || password.isBlank()) {
            return LoginResult.EMPTY_FIELDS;
        }
        if (!credentials.containsKey(key)) {
            return LoginResult.USER_NOT_FOUND;
        }
        if (!credentials.get(key).equals(password)) {
            return LoginResult.WRONG_PASSWORD;
        }
        return LoginResult.SUCCESS;
    }

    public RegisterResult register(String email, String password, String confirmPassword, String name) {
        String key = normalize(email);

        if (key.isEmpty() || password == null || password.isBlank()
                || confirmPassword == null || confirmPassword.isBlank()) {
            return RegisterResult.EMPTY_FIELDS;
        }
        if (!password.equals(confirmPassword)) {
            return RegisterResult.PASSWORD_MISMATCH;
        }
        if (credentials.containsKey(key)) {
            return RegisterResult.EMAIL_EXISTS;
        }

        credentials.put(key, password);
        displayNames.put(key, name == null || name.isBlank() ? key : name.trim());
        return RegisterResult.SUCCESS;
    }

    public boolean userExists(String email) {
        return credentials.containsKey(normalize(email));
    }

    private String normalize(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}
