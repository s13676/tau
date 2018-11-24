Feature: Scooter

  Scenario Outline: Find color by regex
    Given there is a list of objects
    When list is filtered by color regex <regex>
    Then the result should be <result>
    Examples:
      | regex      | result |
      | (B\|b)lack | 2      |
      | red        | 0      |