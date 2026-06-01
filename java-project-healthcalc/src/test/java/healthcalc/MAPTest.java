package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
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

/**
 * Tests for the HealthCalc interface - MAP Metric.
 * * Use the AAA pattern (Arrange, Act, Assert) for the tests.
 * * @author Johana Elena Sánchez
 */
@DisplayName("Tests para el cálculo de la Presión Arterial Media (MAP)")
public class MAPTest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = HealthCalcImpl.getInstance();
    }

    @Nested
    @DisplayName("Cálculo del MAP")
    class MAPMetricTests {

        @Test
        @DisplayName("Cálculo estándar: PAS 120, PAD 80 debe dar 93.33")
        void testMapEstandard() throws InvalidHealthDataException {
            float pas = 120;
            float pad = 80;
            float expectedMap = (pas + 2 * pad) / 3;

            float result = healthCalc.calculateMAP(pas, pad);

            assertEquals(expectedMap, result, 0.01f);
        }

        @Test
        @DisplayName("Lanzar excepción ante valores negativos o cero")
        void testMapValoresNulosONegativos() {
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class, () -> healthCalc.calculateMAP(-120, 80)),
                () -> assertThrows(InvalidHealthDataException.class, () -> healthCalc.calculateMAP(120, 0))
            );
        }

        @Test
        @DisplayName("Lanzar excepción ante inconsistencia biológica (PAD >= PAS)")
        void testMapInconsistencia() {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.calculateMAP(70, 110)); //pad mayor que pas
        }

        @Test
        @DisplayName("Error con presiones imposibles")
        void testLimitesFisicos() {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.calculateMAP(350, 80)); 
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.calculateMAP(120, 250));
}
    }

    @Nested
    @DisplayName("Clasificación a partir del MAP")
    class MAPClassificationTests {

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como Low")
        @ValueSource(floats = {40, 60, 69.99f})  //repito con varios valores MAP en un mismo test para validar que la métrica clasifica correctamente en un rango
        @DisplayName("Validación de categoría Low (hay una perfusión baja)")
        void testMapLow(float map) throws InvalidHealthDataException {
            String expected = "Low";
            String result = healthCalc.mapClassification(map);
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como Normal")
        @ValueSource(floats = {70, 85, 100})
        @DisplayName("Validación de categoría Normal (hay una perfusión saludable)")
        void testMapNormal(float map) throws InvalidHealthDataException {
            String expected = "Normal";
            String result = healthCalc.mapClassification(map);
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como High")
        @ValueSource(floats = {100.1f, 120, 180})
        @DisplayName("Validación de categoría High (Hay una perfusión alta)")
        void testMapHigh(float map) throws InvalidHealthDataException {
            String expected = "High";
            String result = healthCalc.mapClassification(map);
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "Valor de clasificación inválido: {0}")
        @ValueSource(floats = {-10, 0})
        @DisplayName("Lanzar excepción si se intenta clasificar un MAP nulo o negativo")
        void testMapClassificationInvalid(float map) {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.mapClassification(map));
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como {1}")
        @CsvSource({
            "60, Low",
            "70, Normal",
            "100, Normal",
            "100.1, High"
        })
        @DisplayName("Clasificación de MAP en los límites exactos")
        void testMapClassificationLimites(float map, String expectedCategory) throws InvalidHealthDataException {
            assertEquals(expectedCategory, healthCalc.mapClassification(map));
        }
    }
}