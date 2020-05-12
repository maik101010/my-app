Feature: Falta una password
  Con el fin de poder validar respuesta de password faltante
  Yo como usuario intento loguearme sin una password correcta

  Scenario: Cliente intenta loguearse

    Given Michael es un cliente que esta ingresando sin contrasena
    When el intenta poner un email sin contraseña
    Then el deberia obtener un error de respuesta