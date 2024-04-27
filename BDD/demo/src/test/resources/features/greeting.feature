Feature: Testing a REST API
  Los usuarios deben ser capaces de llamar a un GET y a un POST y probar la funcionalidad ent to end

  Scenario: Saludar a un usuario por nombre
    Given Tenemos un usuario con id '1'
    When el usuario desea ser saludado
    Then el saludo solicitado 'Hello Test' es retornado