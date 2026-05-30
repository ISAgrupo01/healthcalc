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

@DisplayName("Tests para el cálculo de la Presión Arterial Media (MAP)")
public class MAPTest {

    private HealthCalc healthCalc;
    private OtraMetrica metrica;

    @BeforeEach
    void setUp() {
        healthCalc = HealthCalcImpl.getInstance();
        metrica = (OtraMetrica) healthCalc;
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

            float result = metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, pas, pad));

            assertEquals(expectedMap, result, 0.01f);
        }

        @Test
        @DisplayName("Lanzar excepción ante valores negativos o cero")
        void testMapValoresNulosONegativos() {
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class, () -> metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, -120, 80))),
                () -> assertThrows(InvalidHealthDataException.class, () -> metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, 120, 0)))
            );
        }

        @Test
        @DisplayName("Lanzar excepción ante inconsistencia biológica (PAD >= PAS)")
        void testMapInconsistencia() {
            assertThrows(InvalidHealthDataException.class, () -> metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, 70, 110)));
        }

        @Test
        @DisplayName("Error con presiones imposibles")
        void testLimitesFisicos() {
            assertThrows(InvalidHealthDataException.class, () -> metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, 350, 80))); 
            assertThrows(InvalidHealthDataException.class, () -> metrica.m(new PersonImpl(0, 0, Gender.MALE, 0, 120, 250)));
        }
    }

    @Nested
    @DisplayName("Clasificación a partir del MAP")
    class MAPClassificationTests {

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como Low")
        @ValueSource(floats = {40, 60, 69.99f})  
        @DisplayName("Validación de categoría Low (hay una perfusión baja)")
        void testMapLow(float map) throws InvalidHealthDataException {
            String expected = "LOW";
            String result = metrica.mapCategory(new PersonImpl(0, 0, Gender.MALE, 0, map + 20, map - 10)).toString();
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como Normal")
        @ValueSource(floats = {70, 85, 100})
        @DisplayName("Validación de categoría Normal (hay una perfusión saludable)")
        void testMapNormal(float map) throws InvalidHealthDataException {
            String expected = "NORMAL";
            String result = metrica.mapCategory(new PersonImpl(0, 0, Gender.MALE, 0, map + 20, map - 10)).toString();
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como High")
        @ValueSource(floats = {100.1f, 120, 180})
        @DisplayName("Validación de categoría High (Hay una perfusión alta)")
        void testMapHigh(float map) throws InvalidHealthDataException {
            String expected = "HIGH";
            String result = metrica.mapCategory(new PersonImpl(0, 0, Gender.MALE, 0, map + 20, map - 10)).toString();
            assertEquals(expected, result);
        }

        @ParameterizedTest(name = "Valor de clasificación inválido: {0}")
        @ValueSource(floats = {-10, 0})
        @DisplayName("Lanzar excepción si se intenta clasificar un MAP nulo o negativo")
        void testMapClassificationInvalid(float map) {
            assertThrows(InvalidHealthDataException.class, () -> metrica.mapCategory(new PersonImpl(0, 0, Gender.MALE, 0, map + 20, map - 10)));
        }

        @ParameterizedTest(name = "MAP {0} debe ser clasificado como {1}")
        @CsvSource({
            "60, LOW",
            "70, NORMAL",
            "100, NORMAL",
            "100.1, HIGH"
        })
        @DisplayName("Clasificación de MAP en los límites exactos")
        void testMapClassificationLimites(float map, String expectedCategory) throws InvalidHealthDataException {
            assertEquals(expectedCategory, metrica.mapCategory(new PersonImpl(0, 0, Gender.MALE, 0, map + 20, map - 10)).toString());
        }
    }
}