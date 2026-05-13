package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;
import java.util.Scanner;

public class MainP1 {
    public static void main(String[] args) {
        HealthCalc calculator = HealthCalcImpl.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CALCULADORA DE SALUD GRUPAL ===");
        System.out.println("1. Calcular BMI (Índice de Masa Corporal) - Versión FULL");
        System.out.println("2. Calcular MAP (Presión Arterial Media)");
        System.out.println("3. Calculadora de IBW (Peso Ideal)");
        System.out.println("4. Prueba Adaptador Hospital Costa del Sol de Marbella");
        System.out.print("Selecciona una opción: ");

        int opcion = scanner.nextInt();
        
        try {
          
            if (opcion == 1) {
                //BMI
                System.out.print("Introduce tu peso (kg): ");
                double weight = scanner.nextDouble();

                System.out.print("Introduce tu altura (m): ");
                double height = scanner.nextDouble();

                double bmi = calculator.bmi(weight, height);
                String clasificacion = calculator.bmiClassification(bmi);

                System.out.println("\n--- RESULTADOS ---");
                System.out.printf("Valor BMI: %.2f kg/m2\n", bmi);
                System.out.println("Clasificación OMS (FULL): " + clasificacion);
            
            } else if (opcion == 2) {
                //MAP
                System.out.print("Introduce la Presión Sistólica (PAS): ");
                float pas = scanner.nextFloat();

                System.out.print("Introduce la Presión Diastólica (PAD): ");
                float pad = scanner.nextFloat();

                float map = calculator.calculateMAP(pas, pad);
                String clasificacion = calculator.mapClassification(map);

                System.out.println("\n--- RESULTADOS ---");
                System.out.printf("Valor MAP: %.2f mmHg\n", map);
                System.out.println("Clasificación: " + clasificacion);
              
            } else if (opcion == 3) {
                //IBW
                System.out.print("Ingrese su altura en cm: ");
                int altura = scanner.nextInt();
                System.out.print("Escriba su sexo (Hombre o Mujer): ");
                String sexo = scanner.next().toLowerCase(); 
                double ibw = calculator.ibw(altura, sexo);
                System.out.println("Su IBW es: " + ibw + " kg");
            
            } else if (opcion == 4) {
                //PRUEBA PATRÓN ADAPTER
                System.out.println("\n--- SISTEMA HOSPITAL COSTA DEL SOL DE MARBELLA---");
                HealthHospital hospitalSystem = new HealthHospitalAdapter();

                System.out.print("Introduce altura (m): ");
                float altura = scanner.nextFloat();

                System.out.print("Introduce peso (g): ");
                int pesoGramos = scanner.nextInt();

                System.out.print("Introduce género ('M' para Mujer, 'H' para Hombre): ");
                char genero = scanner.next().charAt(0);

                //Probar Índice de Masa Corporal (BMI) con la interfaz del hospital
                Tuple<Float, String> resultadoBMI = hospitalSystem.indiceMasaCorporal(altura, pesoGramos);
                System.out.println("\n--- RESULTADOS HOSPITAL ---");
                System.out.printf("Su Índice de Masa Corporal es: %.2f\n", resultadoBMI.getX());
                System.out.println("Clasificación: " + resultadoBMI.getY());

                //Probar Peso Corporal Ideal con la interfaz del hospital
                int resultadoIBW = hospitalSystem.pesoCorporalIdeal(genero, altura);
                System.out.println("Su Peso Corporal Ideal es: " + resultadoIBW + " kg");

            } else {
                System.out.println("Opción no válida.");
            }    

        } catch (InvalidHealthDataException e) {
            System.err.println("\nERROR DE DATOS: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\nERROR: Entrada no válida. Por favor, usa números.");
        } finally {
            scanner.close();
            System.out.println("Saliendo del programa...");
        }
    }
}

