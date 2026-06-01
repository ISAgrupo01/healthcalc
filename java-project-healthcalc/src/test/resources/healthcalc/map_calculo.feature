# language: es
Característica: Cálculo de la Presión Arterial Media (MAP)

  Como usuario
  Quiero calcular la presión arterial media a partir de la presión sistólica y diastólica
  Para conocer mi presión media de forma correcta

  Escenario: Calcular la MAP con valores válidos
    Dado una presión sistólica de 120 mmHg
    Y una presión diastólica de 80 mmHg
    Cuando solicito calcular la MAP
    Entonces el sistema muestra el valor "93.33" mmHg

  Escenario: Error por valores negativos o iguales a cero
    Dado una presión sistólica de -120 mmHg
    Y una presión diastólica de 0 mmHg
    Cuando solicito calcular la MAP
    Entonces el sistema informa de que los datos no son válidos

  Escenario: Error por inconsistencia biológica
    Dado una presión sistólica de 70 mmHg
    Y una presión diastólica de 110 mmHg
    Cuando solicito calcular la MAP
    Entonces el sistema informa de que los datos no son válidos

  Escenario: Error por valores fuera de los límites físicos
    Dado una presión sistólica de 350 mmHg
    Y una presión diastólica de 220 mmHg
    Cuando solicito calcular la MAP
    Entonces el sistema informa de que los datos no son válidos