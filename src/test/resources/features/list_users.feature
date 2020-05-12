Feature: Visualizacion lista empleados
  Con el fin de poder visualizar los empleados
  Yo como usuario quiero poder buscar los empleados
  Para poder visualizar la información de cada uno de ellos

  Scenario: Cliente busca empleados

    Given Michael es un cliente que quiere buscar informacion de los empleados
    When el intenta buscar los empleados
    Then el deberia obtener 6 empleados
    And el primer nombre de la lista debe ser cerulean