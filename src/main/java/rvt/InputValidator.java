package rvt;

import java.util.regex.Pattern;

public class InputValidator {
    // Vārds/Uzvārds: vismaz 3 burti (iekļaujot latviešu valodas burtus)
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZāčēģīķļņšūžĀČĒĢĪĶĻŅŠŪŽ]{3,}$");
    // Standarta e-pasta formāts
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    // Personas kods: 6 cipari, domuzīme, 5 cipari
    private static final Pattern PK_PATTERN = Pattern.compile("^\\d{6}-\\d{5}$");

    public static boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPersonalCode(String pk) {
        return PK_PATTERN.matcher(pk).matches();
    }
}