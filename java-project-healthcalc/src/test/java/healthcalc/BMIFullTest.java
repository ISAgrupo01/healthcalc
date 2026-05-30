package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;

@DisplayName("Tests para la calculadora de salud- BMI versión FULL.")
public class BMIFullTest {
    private HealthCalc healthCalc;
    private BasalMetabolicIndex metrica;

    @BeforeEach
    void setUp() {
        healthCalc = HealthCalcImpl.getInstance();
        metrica = (BasalMetabolicIndex) healthCalc;
    }

    @Nested
    @DisplayName("Métrica del BMI")
    class BMIMetricTests {

        @Test
        @DisplayName("Cálculo de BMI con valores estándar válidos")
        void testBmiValido() throws InvalidHealthDataException {
            float weight = 70.0f;
            float height = 1.75f;
            double expectedBmi = 70.0 / Math.pow(1.75, 2);

            double result = metrica.basalMetabolicIndex(new PersonImpl(weight, height, Gender.MALE, 0));

            assertEquals(expectedBmi, result, 0.01);
        }

        @Test
        @DisplayName("Lanzar excepción cuando el peso es cero")
        void testBmiPesoCero() {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(0f, 1.70f, Gender.MALE, 0)));
        }

        @Test
        @DisplayName("Lanzar excepción cuando la altura es cero")
        void testBmiAlturaCero() {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(70f, 0f, Gender.MALE, 0)));
        }

        @Test
        @DisplayName("Lanzar excepción cuando los valores son negativos")
        void testBmiNegativos() {
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(-70f, 1.70f, Gender.MALE, 0))),
                () -> assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(70f, -1.70f, Gender.MALE, 0)))
            );
        }

        @ParameterizedTest(name = "Peso mínimo inválido: {0} kg")
        @ValueSource(doubles = {-10.0, 0.0, 0.99})
        @DisplayName("Bloqueo de pesos inferiores al límite biológico mínimo (1 kg)")
        void testPesoMinimoImposible(double weight) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl((float)weight, 1.70f, Gender.MALE, 0)));
        }

        @ParameterizedTest(name = "Peso máximo inválido: {0} kg")
        @ValueSource(doubles = {700.1, 1000.0, 5000.0})
        @DisplayName("Bloqueo de pesos superiores al límite biológico máximo (700 kg)")
        void testPesoMaximoImposible(double weight) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl((float)weight, 1.70f, Gender.MALE, 0)));
        }

        @ParameterizedTest(name = "Altura mínima inválida: {0} m")
        @ValueSource(doubles = {-0.50, 0.0, 0.29})
        @DisplayName("Bloqueo de alturas inferiores al límite biológico mínimo (0.30 m)")
        void testAlturaMinimaImposible(double height) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(70f, (float)height, Gender.MALE, 0)));
        }

        @ParameterizedTest(name = "Altura máxima inválida: {0} m")
        @ValueSource(doubles = {3.01, 3.50, 5.00})
        @DisplayName("Bloqueo de alturas superiores al límite biológico máximo (3.00 m)")
        void testAlturaMaximoImposible(double height) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.basalMetabolicIndex(new PersonImpl(70f, (float)height, Gender.MALE, 0)));
        }
    }

    @Nested
    @DisplayName("Clasificación FULL a partir del BMI")
    class BMIClassificationFullTests {
        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Severe thinness")
        @ValueSource(doubles = {10.0, 15.5, 15.9, 15.99})
        @DisplayName("Validación de categoría Severe thinness (Delgadez severa)")
        void testSevereThinness(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("SEVERE_THINNESS", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Moderate thinness")
        @ValueSource(doubles = {16.0, 16.5, 16.9, 16.99})
        @DisplayName("Validación de categoría Moderate thinness (Delgadez moderada)")
        void testModerateThinness(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("MODERATE_THINNESS", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Mild thinness")
        @ValueSource(doubles = {17.0, 17.5, 18.4, 18.49})
        @DisplayName("Validación de categoría Mild thinness (Delgadez leve)")
        void testMildThinness(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("MILD_THINNESS", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Normal weight")
        @ValueSource(doubles = {18.5, 20.0, 24.9, 24.99})
        @DisplayName("Validación de categoría Normal weight (Peso saludable)")
        void testNormalweight(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("NORMAL", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Overweight")
        @ValueSource(doubles = {25.0, 27.5, 29.9, 29.99})
        @DisplayName("Validación de categoría Overweight (Sobrepeso)")
        void testOverweight(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("OVERWEIGHT", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class I (Moderate)")
        @ValueSource(doubles = {30.0, 32.5, 34.9, 34.99})
        @DisplayName("Validación de categoría Obese Class I (Moderate) (Obesidad moderada)")
        void testObeseClassI(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("OBESE_CLASS_I", result);
        }
        
        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class II (Severe)")
        @ValueSource(doubles = {35.0, 37.0, 39.9, 39.99})
        @DisplayName("Validación de categoría Obese Class II (Severe) (Obesidad severa)")
        void testObeseClassII(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("OBESE_CLASS_II", result);
        }

        @ParameterizedTest(name = "BMI {0} debe ser clasificado como Obese Class III (Morbid)")
        @ValueSource(doubles = {40.0, 45.0, 150.0})
        @DisplayName("Validación de categoría Obese Class III (Morbid) (Obesidad mórbida)")
        void testObeseClassIII(double bmi) throws InvalidHealthDataException {
            String result = metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)).toString();
            assertEquals("OBESE_CLASS_III", result);
        }

        @ParameterizedTest(name = "BMI mínimo inválido: {0}")
        @ValueSource(doubles = {-50.0, -1.0, -0.01})
        @DisplayName("Bloqueo de valores de BMI negativos (Error de entrada)")
        void testMinimoImposible(double bmi) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)));
        }

        @ParameterizedTest(name = "BMI máximo extremo: {0}")
        @ValueSource(doubles = {150.1, 200.0, 500.0})
        @DisplayName("Bloqueo de valores de BMI superiores al límite humano razonable (150)")
        void testMaximoImposible(double bmi) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.category(new PersonImpl((float)bmi, 1.0f, Gender.MALE, 0)));
        }
    }
}