package healthcalc;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import healthcalc.exceptions.InvalidHealthDataException;

public class HealthCalcControlador {

    private IHealthCalcView vista;   //trabaja con la interfaz, no con la clase concreta
    private HealthCalc modelo;       //trabaja con la interfaz

    
	
    public HealthCalcControlador(IHealthCalcView vista, HealthCalc modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarListeners();
    }
    private void iniciarListeners() {

        vista.getBtnCalcularBMI().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.getLblErrorBMI().setText("");
                vista.getLblResultadoBMI().setText("Resultado: ---");
                vista.getLblClasificacionBMI().setText("Clasificacion: ---");

                try {
                    String pesoStr = vista.getTxtPeso().getText().trim();
                    String altStr  = vista.getTxtAltura().getText().trim();

                    if (pesoStr.isEmpty() || altStr.isEmpty()) {
                        vista.getLblErrorBMI().setText("Error: debe introducir peso y altura.");
                        return;
                    }

                    double peso    = Double.parseDouble(pesoStr);
                    double alturaCm = Double.parseDouble(altStr);

                    if (peso <= 0 || alturaCm <= 0) {
                        vista.getLblErrorBMI().setText("Error: los valores deben ser mayores a cero.");
                        return;
                    }

                    double alturaM = alturaCm / 100.0;

                    Person person = new PersonImpl((float) peso, (float) alturaM, Gender.MALE, 0);
                    BasalMetabolicIndex metricaBMI = (BasalMetabolicIndex) modelo;
                    float bmi = metricaBMI.basalMetabolicIndex(person);
                    String clasif = metricaBMI.category(person).toString();

                    vista.getLblResultadoBMI().setText("Resultado: " + String.format("%.2f", bmi));
                    vista.getLblClasificacionBMI().setText("Clasificación: " + clasif);

                } catch (NumberFormatException ex) {
                    vista.getLblErrorBMI().setText("Error: ingrese valores numéricos válidos.");
                } catch (InvalidHealthDataException ex) {
                    vista.getLblErrorBMI().setText("Error: " + ex.getMessage());
                }
            }
        });

        vista.getBtnCalcularIBW().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.getLblErrorIBW().setText("");
                vista.getLblResultadoIBW().setText("Resultado: ---");

                try {
                    String altStr = vista.getTxtAlturaIBW().getText().trim();

                    if (altStr.isEmpty()) {
                        vista.getLblErrorIBW().setText("Error: debe introducir la altura.");
                        return;
                    }

                    int altura = Integer.parseInt(altStr);

                    if (altura <= 0) {
                        vista.getLblErrorIBW().setText("Error: la altura debe ser mayor a cero.");
                        return;
                    }

                    String sexo = ((String) vista.getComboSexo().getSelectedItem()).toLowerCase();

                    Person person = new PersonImpl(0f, altura / 100f, sexo.equals("hombre") ? Gender.MALE : Gender.FEMALE, 0);
                    IdealBodyWeight metricaIBW = (IdealBodyWeight) modelo;
                    float ibw = metricaIBW.idealBodyWeight(person);
                    vista.getLblResultadoIBW().setText("Resultado: " + String.format("%.2f", ibw) + " kg");

                } catch (NumberFormatException ex) {
                    vista.getLblErrorIBW().setText("Error: ingrese una altura válida.");
                } catch (InvalidHealthDataException ex) {
                    vista.getLblErrorIBW().setText("Error: " + ex.getMessage());
                }
            }
        });

        vista.getBtnCalcularMAP().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.getLblErrorMAP().setText("");
                vista.getLblResultadoMAP().setText("Resultado: ---");
                vista.getLblClasificacionMAP().setText("Clasificación: ---");

                try {
                    String sisStr = vista.getTxtSistolica().getText().trim();
                    String diaStr = vista.getTxtDiastolica().getText().trim();

                    if (sisStr.isEmpty() || diaStr.isEmpty()) {
                        vista.getLblErrorMAP().setText("Error: debe introducir ambas presiones.");
                        return;
                    }

                    float sistolica  = Float.parseFloat(sisStr);
                    float diastolica = Float.parseFloat(diaStr);

                    if (sistolica <= 0 || diastolica <= 0) {
                        vista.getLblErrorMAP().setText("Error: los valores deben ser mayores a cero.");
                        return;
                    }

                    if (sistolica <= diastolica) {
                        vista.getLblErrorMAP().setText("Error: la sistólica debe ser mayor que la diastólica.");
                        return;
                    }
                    Person person = new PersonImpl(0.0f, 0.0f, Gender.MALE, 0, sistolica, diastolica);

                    OtraMetrica metricaMAP = (OtraMetrica) modelo;
                    float map = metricaMAP.m(person);
                    String clasif = metricaMAP.mapCategory(person).toString();

                    vista.getLblResultadoMAP().setText("Resultado: " + String.format("%.2f", map) + " mmHg");
                    vista.getLblClasificacionMAP().setText("Clasificación: " + clasif);

                } catch (NumberFormatException ex) {
                    vista.getLblErrorMAP().setText("Error: ingrese valores numéricos válidos.");
                } catch (InvalidHealthDataException ex) {
                    vista.getLblErrorMAP().setText("Error: " + ex.getMessage());
                }
            }
        });
    }
}