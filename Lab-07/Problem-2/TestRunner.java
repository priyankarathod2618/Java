import java.lang.reflect.Method;

public class TestRunner {

    @Run
    public void testAddition() {
        System.out.println("testAddition executed");
    }

    @Run
    public void testSubtraction() {
        System.out.println("testSubtraction executed");
    }

    public void normalMethod() {
        System.out.println("normalMethod executed");
    }

    @Run
    public void testMultiplication() {
        System.out.println("testMultiplication executed");
    }

    public static void main(String[] args) {

        TestRunner testRunner = new TestRunner();

        int count = 0;

        for (Method method : TestRunner.class.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                try {
                    method.invoke(testRunner);
                    count++;
                } catch (Exception e) {
                    System.out.println(
                        "Error running " + method.getName()
                    );
                }
            }
        }

        System.out.println();
        System.out.println("Total tests executed: " + count);
    }
}