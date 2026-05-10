package healthcalc;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



//Interfaz de vista para el patrón MVC de la calculadora de salud. El controlador trabaja solo con la interfaz, nunca con la clase concreta HealthCalcView.
public interface IHealthCalcView {

    JFrame getFrame();

    // pestaña BMI
    JTextField getTxtPeso();
    JTextField getTxtAltura();
    JButton    getBtnCalcularBMI();
    JLabel     getLblResultadoBMI();
    JLabel     getLblClasificacionBMI();
    JLabel     getLblErrorBMI();

    // pestaña IBW
    JTextField       getTxtAlturaIBW();
    JComboBox<String> getComboSexo();
    JButton          getBtnCalcularIBW();
    JLabel           getLblResultadoIBW();
    JLabel           getLblErrorIBW();

    // pestaña MAP
    JTextField getTxtSistolica();
    JTextField getTxtDiastolica();
    JButton    getBtnCalcularMAP();
    JLabel     getLblResultadoMAP();
    JLabel     getLblClasificacionMAP();
    JLabel     getLblErrorMAP();
}