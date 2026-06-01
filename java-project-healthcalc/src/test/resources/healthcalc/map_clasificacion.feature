# language: es
Característica: Clasificación del estado de perfusión a partir de la MAP

  Como usuario
  Quiero conocer la clasificación de mi MAP
  Para saber si mi estado es bajo, normal o alto

  Escenario: Clasificar una MAP baja
    Dado un valor de MAP de 65 mmHg
    Cuando solicito clasificar la MAP
    Entonces el sistema muestra la clasificación "Low"

  Escenario: Clasificar una MAP normal
    Dado un valor de MAP de 85 mmHg
    Cuando solicito clasificar la MAP
    Entonces el sistema muestra la clasificación "Normal"

  Escenario: Clasificar una MAP alta
    Dado un valor de MAP de 110 mmHg
    Cuando solicito clasificar la MAP
    Entonces el sistema muestra la clasificación "High"

  Escenario: Clasificar una MAP normal en el límite inferior
    Dado un valor de MAP de 70 mmHg
    Cuando solicito clasificar la MAP
    Entonces el sistema muestra la clasificación "Normal"

  Escenario: Clasificar una MAP normal en el límite superior
    Dado un valor de MAP de 100 mmHg
    Cuando solicito clasificar la MAP
    Entonces el sistema muestra la clasificación "Normal"