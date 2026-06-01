# language: es
Característica: Clasificación completa del BMI

  Como usuario
  Quiero clasificar mi BMI usando la versión Full
  Para conocer con precisión mi estado de peso

  Escenario: Clasificar como Delgadez Severa
    Dado un valor de BMI de 15,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Severe thinness"

  Escenario: Clasificar como Delgadez Moderada
    Dado un valor de BMI de 16,5
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Moderate thinness"

  Escenario: Clasificar como Delgadez Leve
    Dado un valor de BMI de 17,5
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Mild thinness"

  Escenario: Clasificar como Peso Normal
    Dado un valor de BMI de 22,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Normal weight"

  Escenario: Clasificar como Pre-obesidad
    Dado un valor de BMI de 27,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Overweight"

  Escenario: Clasificar como Obesidad Clase I
    Dado un valor de BMI de 32,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Obese Class I (Moderate)"

  Escenario: Clasificar como Obesidad Clase II
    Dado un valor de BMI de 37,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Obese Class II (Severe)"

  Escenario: Clasificar como Obesidad Clase III
    Dado un valor de BMI de 45,0
    Cuando solicito clasificar el BMI
    Entonces el sistema muestra la clasificación BMI "Obese Class III (Morbid)"