import com.healthycoderapp.BMICalculator;
import com.healthycoderapp.Coder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all unit tests");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After all unit tests");
    }

    @ParameterizedTest(name = "weight{0}, height{1}")
    @CsvSource(value = {"89.0, 1.72", "95.0, 1.65", "110.0, 1.78"})
    void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_When_DietNotRecommended() {
        //given
        double weight = 50.0;
        double height = 1.92;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_HeightZero() {
        //given
        double weight = 50.0;
        double height = 0.0;

        //when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

        //then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderIsNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.87, 89.0));
        coders.add(new Coder(1.55, 109.8));
        coders.add(new Coder(1.63, 65.9));
        coders.add(new Coder(1.59, 69.3));
        coders.add(new Coder(1.37, 38.9));

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertAll(
                () -> assertEquals(1.55, coderWorstBMI.getHeight()),
                () -> assertEquals(109.8, coderWorstBMI.getWeight())
        );

    }

    @Test
    void should_ReturnNullWorstBMI_When_CoderListEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertNull(coderWorstBMI);

    }

    @Test
    void should_ReturnCorrectScoreBMIArray_When_CoderListNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.87, 89.0));
        coders.add(new Coder(1.55, 109.0));
        coders.add(new Coder(1.63, 66.0));
        double[] expected = {25.45, 45.37, 24.84};

        //when
        double[] bmiArray = BMICalculator.getBMIScores(coders);

        //then
        assertArrayEquals(expected, bmiArray);
    }


}