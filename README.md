# Diplom_3

## Run tests
Run all tests
```bash
mvn clean test
```
or with parameters
```bash
mvn clean test -Dbrowser=yandex -Dheadless=1 
```
### Parameters
* ```Dbrowser``` - yandex or firefox. Default - chrome
* ```Dheadless``` - 0 or 1. Default - 0
### Generate reporst
```bash
mvn allure:serve 
```