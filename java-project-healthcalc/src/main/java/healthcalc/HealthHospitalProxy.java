package healthcalc;

public class HealthHospitalProxy implements HealthHospital, HealthStats{
    private HealthHospital calculadoraReal;

    private int totalPacientes = 0;

    private float sumaAltura = 0f;
    private int countAltura = 0;

    private float sumaPesoKg = 0f;
    private int countPeso = 0;

    private float sumaImc = 0f;
    private int countImc = 0;

    private int hombres = 0;
    private int mujeres = 0;

    public HealthHospitalProxy(HealthHospital calculadoraReal) {
        this.calculadoraReal = calculadoraReal;
    }

    @Override
    public Tuple<Float, String> indiceMasaCorporal(float altura, int peso) {

        totalPacientes++;

        sumaAltura += altura;
        countAltura++;

        sumaPesoKg += peso / 1000f;
        countPeso++;

        Tuple<Float, String> resultado =
                calculadoraReal.indiceMasaCorporal(altura, peso);

        sumaImc += resultado.getX();
        countImc++;

        return resultado;
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) {

        sumaAltura += altura;
        countAltura++;

        char g = Character.toUpperCase(genero);

        if (g == 'H') {
            hombres++;
        } else if (g == 'M') {
            mujeres++;
        }

        int ibw = calculadoraReal.pesoCorporalIdeal(genero, altura);

        sumaPesoKg += ibw; 
        countPeso++;

        return ibw;
    }

    @Override
    public float alturaMedia() {
        if (countAltura == 0) {
            return 0;
        } else {
            return sumaAltura / countAltura;
        }
    }

    @Override
    public float pesoMedio() {
        if (countPeso == 0) {
            return 0;
        } else {
            return sumaPesoKg / countPeso;
        }
    }

    @Override
    public float imcMedio() {
        if (countImc == 0) {
            return 0;
        } else {
            return sumaImc / countImc;
        }
    }

    @Override
    public int numSexoH() {
        return hombres;
    }

    @Override
    public int numSexoM() {
        return mujeres;
    }

    @Override
    public int numTotalPacientes() {
        return totalPacientes;
    }
}


