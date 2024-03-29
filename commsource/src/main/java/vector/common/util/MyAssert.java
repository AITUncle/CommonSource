package vector.common.util;

import android.os.Looper;
import android.text.TextUtils;


/**
 * 断言类
 * Debug 模式下起作用
 *
 * @author vectorzeng
 */
public class MyAssert {

    private static boolean isDebug = false;

    public static void init(boolean debug) {
        isDebug = debug;
    }

    /**
     * Asserts that two booleans are equal.
     */
    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertSubThread() {
        if (isDebug)
            assertTrue(Looper.getMainLooper() != Looper.myLooper());
    }

    public static void assertMainThread() {
        if (isDebug)
            assertTrue(Looper.getMainLooper() == Looper.myLooper());
    }

    public static void assertNotMainThread() {
        if (isDebug)
            assertTrue(Looper.getMainLooper() != Looper.myLooper());
    }

    /**
     * Asserts that two bytes are equal.
     */
    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two chars are equal.
     */
    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two doubles are equal concerning a delta. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two floats are equal concerning a delta. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two ints are equal.
     */
    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two longs are equal.
     */
    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two objects are equal. If they are not an
     * AssertionFailedError is thrown.
     */
    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two shorts are equal.
     */
    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two booleans are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, boolean expected,
                                    boolean actual) {
        if (!isDebug)
            return;
        assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual));
    }

    /**
     * Asserts that two bytes are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, byte expected, byte actual) {
        if (!isDebug)
            return;
        assertEquals(message, Byte.valueOf(expected), Byte.valueOf(actual));
    }

    /**
     * Asserts that two chars are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, char expected, char actual) {
        if (!isDebug)
            return;
        assertEquals(message, Character.valueOf(expected), Character.valueOf(actual));
    }

    /**
     * Asserts that two doubles are equal concerning a delta. If they are not an
     * AssertionFailedError is thrown with the given message. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(String message, double expected,
                                    double actual, double delta) {
        if (!isDebug)
            return;

        // handle infinity specially since subtracting to infinite values gives
        // NaN and the
        // the following test fails
        if (Double.isInfinite(expected)) {
            if (!(expected == actual))
                failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
        } else if (!(Math.abs(expected - actual) <= delta)) // Because
            // comparison
            // with NaN always
            // returns false
            failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
    }

    /**
     * Asserts that two floats are equal concerning a delta. If they are not an
     * AssertionFailedError is thrown with the given message. If the expected
     * value is infinity then the delta value is ignored.
     */
    public static void assertEquals(String message, float expected,
                                    float actual, float delta) {
        if (!isDebug)
            return;

        // handle infinity specially since subtracting to infinite values gives
        // NaN and the
        // the following test fails
        if (Float.isInfinite(expected)) {
            if (!(expected == actual))
                failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
        } else if (!(Math.abs(expected - actual) <= delta))
            failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
    }

    /**
     * Asserts that two ints are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, int expected, int actual) {
        if (!isDebug)
            return;
        assertEquals(message, Integer.valueOf(expected), Integer.valueOf(actual));
    }

    /**
     * Asserts that two longs are equal. If they are not an AssertionFailedError
     * is thrown with the given message.
     */
    public static void assertEquals(String message, long expected, long actual) {
        if (!isDebug)
            return;
        assertEquals(message, Long.valueOf(expected), Long.valueOf(actual));
    }

    /**
     * Asserts that two objects are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, Object expected,
                                    Object actual) {
        if (!isDebug)
            return;

        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;
        failNotEquals(message, expected, actual);
    }

    /**
     * Asserts that two shorts are equal. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertEquals(String message, short expected, short actual) {
        if (!isDebug)
            return;
        assertEquals(message, new Short(expected), new Short(actual));
    }

    /**
     * Asserts that two Strings are equal.
     */
    public static void assertEquals(String expected, String actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two Strings are equal.
     */
    public static void assertEquals(String message, String expected,
                                    String actual) {
        if (!isDebug)
            return;

        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;
        throw new AssertionError("" + message
                + ",expected=" + expected + "actual=" + actual);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an
     * AssertionFailedError.
     */
    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an
     * AssertionFailedError with the given message.
     */
    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    /**
     * Asserts that an object isn't null.
     */
    public static void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    public static void assertNotEmpty(String str){
        if (TextUtils.isEmpty(str)){
            fail("empty str=" + str);
        }
    }

    /**
     * Asserts that an object isn't null. If it is an AssertionFailedError is
     * thrown with the given message.
     */
    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not the
     * same an AssertionFailedError is thrown.
     */
    public static void assertNotSame(Object expected, Object actual) {
        assertNotSame(null, expected, actual);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertNotSame(String message, Object expected,
                                     Object actual) {
        if (expected == actual)
            failSame(message);
    }

    /**
     * Asserts that an object is null.
     */
    public static void assertNull(Object object) {
        assertNull(null, object);
    }

    /**
     * Asserts that an object is null. If it is not an AssertionFailedError is
     * thrown with the given message.
     */
    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not the
     * same an AssertionFailedError is thrown.
     */
    public static void assertSame(Object expected, Object actual) {
        assertSame(null, expected, actual);
    }

    /**
     * Asserts that two objects refer to the same object. If they are not an
     * AssertionFailedError is thrown with the given message.
     */
    public static void assertSame(String message, Object expected, Object actual) {
        if (expected == actual)
            return;
        failNotSame(message, expected, actual);
    }

    /**
     * Asserts that a condition is true. If it isn't it throws an
     * AssertionFailedError.
     */
    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    /**
     * Asserts that a condition is true. If it isn't it throws an
     * AssertionFailedError with the given message.
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition)
            fail(message);
    }

    /**
     * Fails a test with no message.
     */
    public static void fail() {
        fail(null);
    }

    /**
     * Fails a test with the given message.
     */
    public static void fail(String message) {
        if (isDebug) {
            throw new AssertionError(message);
        }
    }

    private static void failNotEquals(String message, Object expected,
                                      Object actual) {
        if (!isDebug)
            return;
        fail(format(message, expected, actual));
    }

    private static void failNotSame(String message, Object expected,
                                    Object actual) {
        if (!isDebug)
            return;
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        fail(formatted + "expected same:<" + expected + "> was not:<" + actual
                + ">");
    }

    private static void failSame(String message) {
        if (!isDebug)
            return;
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        fail(formatted + "expected not same");
    }

    static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null)
            formatted = message + " ";
        return formatted + "expected:<" + expected + "> but was:<" + actual
                + ">";
    }

    protected MyAssert() {
    }
}
