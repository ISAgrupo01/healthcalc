# language: es
Característica: Cálculo del peso corporal ideal (IBW)

    Como usuario
    Quiero calcular el peso corporal ideal usando la fórmula de Lorentz a partir de mi sexo y mi altura
    Para conocer mi peso óptimo.

    Antecedentes:
    Dado la calculadora de IBW está iniciada

    Escenario: Calcular el IBW con valores correctos
        Dado una altura de 180 cm
        Y un sexo de "hombre"
        Cuando solicito calcular el IBW
        Entonces el sistema muestra el valor 72,5 kg

    Escenario: Error por sexo distinto a hombre o mujer
        Dado una altura de 180 cm
        Y un sexo de "fluido"
        Cuando solicito calcular el IBW
        Entonces el sistema informa que los datos no son los adecuados

    Escenario: Error por valores negativos o iguales a cero
        Dado una altura de 0 cm
        Y un sexo de "mujer"
        Cuando solicito calcular el IBW
        Entonces el sistema informa que los datos no son los adecuados

    Escenario: Error por valores fuera de los límites físicos
        Dado una altura de 750 cm
        Y un sexo de "mujer"
        Cuando solicito calcular el IBW
        Entonces el sistema informa que los datos no son los adecuados