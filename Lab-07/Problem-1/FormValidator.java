import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormValidator {

    public static List<String> validate(Object object) {

        List<String> errors = new ArrayList<>();

        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int maxLength = annotation.value();

                    if (value != null &&
                        value.toString().length() > maxLength) {

                        errors.add(
                            field.getName()
                            + " must not exceed "
                            + maxLength
                            + " characters"
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add(
                    "Could not access field: " + field.getName()
                );
            }
        }

        return errors;
    }

    public static void main(String[] args) {

        SignupForm validForm = new SignupForm(
            "Priyanka",
            "priyanka@example.com",
            "password123"
        );

        SignupForm invalidForm = new SignupForm(
            "",
            "thisisanextremelylongemailaddressthatexceedsthelimit@example.com",
            "thispasswordiswaytoolong123456789"
        );

        System.out.println("===== FORM VALIDATOR =====");
        System.out.println();

        System.out.println("Valid Form:");

        List<String> validErrors = validate(validForm);

        if (validErrors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            for (String error : validErrors) {
                System.out.println("- " + error);
            }
        }

        System.out.println();

        System.out.println("Invalid Form:");

        List<String> invalidErrors = validate(invalidForm);

        if (invalidErrors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            for (String error : invalidErrors) {
                System.out.println("- " + error);
            }
        }
    }
}