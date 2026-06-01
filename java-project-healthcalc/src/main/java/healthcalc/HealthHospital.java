package healthcalc;

public interface HealthHospital {
    // altura en m, peso en g
    Tuple<Float, String> indiceMasaCorporal(float altura, int peso); 
    int pesoCorporalIdeal(char genero, float altura); 
}