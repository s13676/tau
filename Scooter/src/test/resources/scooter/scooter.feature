Feature: Scooter

  Scenario Outline: Find color by regex
    Given there is a list of objects
      | id | model  | brand | productionYear | color |
      | 1  | Yamaha | Aerox | 2005           | Black |
      | 2  | Yamaha | Aerox | 2005           | Black |
    When list is filtered by color regex <regex>
    Then the result should be <result>
    Examples:
      | regex      | result |
      | (B\|b)lack | 2      |
      | red        | 0      |

  Scenario Outline: Delete by list
    Given there is a list of objects
      | id | model  | brand | productionYear | color |
      | 1  | Yamaha | Aerox | 2005           | Black |
      | 2  | Yamaha | Aerox | 2005           | Black |
    And removing
      | 1 |
      | 2 |
    Then the result should be <correct>
    But the result should not be <wrong>
    Examples:
      | correct | wrong |
      | 0       | 1     |

