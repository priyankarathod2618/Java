import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ColumnMapper {

    public static Student populateStudent(String[] headers, String[] data)
            throws Exception {

        Student student = new Student();

        Map<String, String> row = new HashMap<>();

        for (int i = 0; i < headers.length && i < data.length; i++) {
            row.put(headers[i], data[i]);
        }

        for (Field field : Student.class.getDeclaredFields()) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();

                if (!row.containsKey(columnName)) {
                    System.out.println(
                        "Missing column: " + columnName
                    );
                    continue;
                }

                String value = row.get(columnName);

                field.setAccessible(true);

                if (field.getType() == int.class) {
                    field.setInt(student, Integer.parseInt(value));
                } else if (field.getType() == String.class) {
                    field.set(student, value);
                }
            }
        }

        return student;
    }

    public static void main(String[] args) throws Exception {

        String[] headers = {
            "id",
            "name",
            "email",
            "age"
        };

        String[] data = {
            "101",
            "Priyanka",
            "priyanka2618@gmail.com",
            "18"
        };

        System.out.println("===== COLUMN MAPPER =====");
        System.out.println();

        Student student = populateStudent(headers, data);

        System.out.println("Student details:");
        student.display();
    }
}