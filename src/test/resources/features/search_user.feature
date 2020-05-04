Feature: Buscar usuario
  Con el fin de poder visualizar información de un usuario,
  Yo como cliente del sistema puedo buscar usuarios
  @Signup
  Scenario: Buscar usuario exitosamente
    Given Michael es un cliente que quiere poder ver información de un usuario
    When el envia la informacion requerida para la busqueda
    Then el debe obtener unos datos de un usuario que correspondan a la informacion requerida