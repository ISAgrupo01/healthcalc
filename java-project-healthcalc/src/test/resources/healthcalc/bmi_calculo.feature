# language: es
Característica: Cálculo del Índice de Masa Corporal (BMI)

  Como usuario
  Quiero calcular mi BMI a partir de mi peso y altura
  Para conocer mi índice de masa corporal de forma correcta

  Escenario: Calcular el BMI con valores válidos
    Dado un peso de 70,0 kg
    Y una altura de 1,70 m
    Cuando solicito calcular el BMI
    Entonces el sistema muestra el resultado de BMI "24.22"

  Escenario: Error por valores no válidos
    Dado un peso de -50,0 kg
    Y una altura de 0.0 m
    Cuando solicito calcular el BMI
    Entonces el sistema informa de que los datos de BMI no son válidos
 
  Escenario: Error por valores fuera de límites físicos
    Dado un peso de 800,0 kg
    Y una altura de 0,40 m
    Cuando solicito calcular el BMI
    Entonces el sistema informa de que los datos de BMI no son válidos