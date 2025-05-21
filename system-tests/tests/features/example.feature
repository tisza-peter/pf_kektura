Feature: Google keresés

  Scenario: Megnyitja a Google-t
    Given a felhasználó megnyitja a "https://www.google.com" oldalt
    Then a cím tartalmazza a "Google" szót
