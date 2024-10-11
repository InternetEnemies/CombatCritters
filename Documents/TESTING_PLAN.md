# Testing Plan
## Overall
- The respective readmes for each repo should contain details on how to run tests
## Unit Tests
- If a wrapper(ts / sharp) contains critical logic it should be tested with unit tests
- The api and logic layers should be tested with 100% method and >80% line coverage
## Integration Tests
- API, logic, and data layers should have >90% method coverage and 100% class coverage
- the wrappers should have 100% method coverage
## System tests
Not required but are welcome.
## Acceptance tests
- all users stories should have at least 1 acceptance test for each ui they are relevant to