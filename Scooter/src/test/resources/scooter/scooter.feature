Feature: Scooter

  Scenario Outline: Find color by regex
    Given there is a list of objects
    When list is filtered by color regex <a>
    Then the result should be <result>
    Examples:
      | a          | result |
      | "B\|black" | 2      |
      | red        | 0      |