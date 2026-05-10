package healthcalc.steps;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class MapSteps {
    private HealthCalcImpl calc = HealthCalcImpl.getInstance();
    private float pas;
    private float pad;
    private float resultadoMAP;
    private String clasificacion;
    private boolean errorLanzado;

    // para map_calculo.feature
    @Dado("una presión sistólica de {float} mmHg")
    public void presionSistolica(float valor) {
        this.pas = valor;
    }
    @Y("una presión diastólica de {float} mmHg")
    public void presionDiastolica(float valor) {
        this.pad = valor;
    }

    @Cuando("solicito calcular la MAP")
    public void calcularMAP() {
        try {
            resultadoMAP =calc.calculateMAP(pas, pad);
            errorLanzado= false;
        } 
        catch (InvalidHealthDataException e) {

            errorLanzado = true;
        }
    }

    @Entonces("el sistema muestra el valor {string} mmHg")
    public void verificarValorMAP(String esperado) {
        assertEquals(Float.parseFloat(esperado), resultadoMAP , 0.01f);
    }

    @Entonces("el sistema informa de que los datos no son válidos")
    public void verificarError() {

        assertTrue(errorLanzado);
    }

    //para map_clasificacion.feature
    @Dado("un valor de MAP de {float} mmHg")
    public void valorMAP(float valor) {

        this.resultadoMAP = valor;
    }

    @Cuando("solicito clasificar la MAP")
    public void clasificarMAP() {

        clasificacion = calc.mapClassification(resultadoMAP);
    }

    @Entonces("el sistema muestra la clasificación {string}")
    public void verificarClasificacion(String esperada) {

        assertEquals(esperada, clasificacion);
    }
}
