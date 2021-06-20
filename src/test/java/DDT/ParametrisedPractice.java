package DDT;

import com.google.common.base.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.lang.annotation.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametrisedPractice {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(ParametrisedPractice.isOdd(number));
    }

    @ParameterizedTest
    @CsvSource({"sElEnIUm,SELENIUM", "calciuM,CALCIUM", "HunGariCum,HUNGARICUM"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/forras.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(
            String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue(Strings.isBlank(input));
    }

    // Custom annotation starts from here, and apply VariableArgumentsProvider class
    static Stream<Arguments> arguments = Stream.of(
            Arguments.of(null, true), // null strings should be considered blank
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("not blank", false)
    );

    @ParameterizedTest
    @VariableSource("arguments")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(
            String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @ArgumentsSource(VariableArgumentsProvider.class)
    public @interface VariableSource {

        /**
         * The name of the static variable
         */
        String value();
    }
    // Custom annotation ends here

    @ParameterizedTest
    @CsvSource({"Isaac,,Newton,Isaac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
        String firstName = argumentsAccessor.getString(0);
        String middleName = (String) argumentsAccessor.get(1);
        String lastName = argumentsAccessor.get(2, String.class);
        String expectedFullName = argumentsAccessor.getString(3);

        Person person = new Person(firstName, middleName, lastName);
        assertEquals(expectedFullName, person.fullName());
    }

    private static class Strings {
        public static boolean isBlank(String input) {
            return input == null || input.trim().isEmpty();
        }
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }



}
