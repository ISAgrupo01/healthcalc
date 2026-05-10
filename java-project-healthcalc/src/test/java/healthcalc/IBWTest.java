package healthcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;

@DisplayName("Tests para la el calculo del IBW.")
public class IBWTest {
    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = HealthCalcImpl.getInstance();
    }

    @Nested
    @DisplayName("Métrica del IBW")
    class IBWMetricTests {
        @Test
        @DisplayName("Cálculo de IBW para hombre con altura estándar")
        void testIbwHombre() throws InvalidHealthDataException {
            int height = 175;
            double expectedIbw = 68.75; // Calculated for height 175cm, male

            double result = healthCalc.ibw(height, "hombre");
            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Cálculo de IBW para mujer con altura estándar")
        void testIbwMujer() throws InvalidHealthDataException {
            int height = 165;
            double expectedIbw = 59.0; // Calculated for height 165cm, female 
            
            double result = healthCalc.ibw(height, "mujer");
            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Lanzar excepción para altura por debajo del rango válido")
        void testIbwHeightBelowRange() {
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(100, "hombre");
            });
        }

        @Test
        @DisplayName("Lanzar excepción para sexo no válido")
        void testIbwInvalidGender() {
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(175, "nobinario");
            });
        }

        @Test
        @DisplayName("Lanzar excepción para sexo nulo")
        void testIbwNullGender() {
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(175, null);
            });
        }

        @Test
        @DisplayName("Lanzar excepción para altura negativa")
        void testIbwNegativeHeight() {
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(-175, "hombre");
            });
        }

        @Test
        @DisplayName("Lanzar excepción para altura cero o nula")
        void testIbwZeroHeight() {
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(0, "hombre");
            });
        }

        @ParameterizedTest(name = "Altura minima inválida: {0} cm")
        @ValueSource(ints = {-100, 0, 149})
        @DisplayName("Bloqueo de alturas inferiores al rango mínimo (150 cm)")
        void testIbwMinHeightInvalid(int height) {
            
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(height, "hombre");
            });
        }

        @ParameterizedTest(name = "Altura máxima inválida: {0} cm")
        @ValueSource(ints = {351, 400, 500})
        @DisplayName("Bloqueo de alturas superiores al rango máximo (350 cm)")
        void testIbwMaxHeightInvalid(int height) {
            
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(height, "hombre");
            });
        }

        @ParameterizedTest(name = "Sexo no válido: {0}")
        @ValueSource(strings = {"nobinario", "otro", "desconocido", ""})
        @DisplayName("Bloqueo de sexos no válidos")
        void testIbwInvalidGender(String sexo) {
            int height = 175;
            
            assertThrows(InvalidHealthDataException.class, () -> {
                healthCalc.ibw(height, sexo);
            });
        }

        @ParameterizedTest(name = "Valores válidos: altura={0} cm, sexo={1}, IBW esperado={2} kg")
        @CsvSource({
            "150,hombre,50.0",
            "175,hombre,68.75",
            "180,hombre,72.5",
            "160,mujer,56.0",
            "170,mujer,62.0"
        })
        @DisplayName("Verificación de cálculo de IBW con valores válidos")
        void testIbwValidValues(int height, String sexo, double expectedIbw) {
            assertEquals(expectedIbw, healthCalc.ibw(height, sexo), 0.1);
        }
    }
}
