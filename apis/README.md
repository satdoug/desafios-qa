# Idwall - Desafio de automação de testes de API

## Como executar testes através do Docker / Jenkins
* Pre-requisitos: [Docker](https://docs.docker.com/install/) instalado no host / máquina local 
* Executar o seguite comando em um terminal: 
```text
$ docker run -p 8080:8080 -p 50000:50000 sfrubio/sfrubio:desafio-qa-jenkins
```
* Verificar se o contâiner Docker está em execução em seu host. Em um terminal, executar o seguinte comando:
```text
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                              NAMES
339dc64edf95        08b0e7913330        "/sbin/tini -- /usr/…"   5 hours ago         Up 5 hours          0.0.0.0:8080->8080/tcp, 0.0.0.0:50000->50000/tcp   desafio-qa-jenkins
```
* Autenticar no Jenkins com o usuário `idwall` e senha `desafio_qa`, Acessar o _job_ [idwall-api-test](http://localhost:8080/job/idwall_api_test) e clicar em "Build with parameters"

![Jenkins Main Menu Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-main-menu.png "Jenkins Main Menu Example")
* Preencher os campos apresentados de acordo com os dados requeridos para a execução dos testes.
* Adicionalmente, é permitida a seleção do grupo de testes a serem executados, no combo box "testGroup"
* Para iniciar a execução, clicar no botão "Build"

![Jenkins Build With Parameters Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-build-with-parameters.png "Jenkins Build With Parameters Example")
* Após a execução dos testes, os resultados são amazenados em [Relatórios do Allure](http://localhost:8080/job/idwall_api_test/allure/):

![Jenkins Allure Reports Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-allure-reports.png "Jenkins Allure Reports Example")
## Como executar testes com o Maven
### Executar os testes com os parãmetros padrão
```text
$ mvn clean test
```
### Executar os testes informando parâmetros
```text
$ mvn clean test -DvalidName="Valid Name" -DvalidNumber="Valid Document Number" -DvalidCompanyNumber="Valid Company Number" -DvalidBirthday=<Valid Date> -Dtoken=<Valid Token>
```
Parâmetros disponíveis e valores padrão:
* `-DvalidName=Test`
* `-DvalidNumber=00000000191`
* `-DvalidCompanyNumber=00000000000191`
* `-DvalidBirthday=1982-11-12`
* `-Dtoken=00000000-0000-0000-0000-000000000000`
### Executar os testes de uma funcionalidade específica
```text
$ mvn clean test -DtestGroup="functionality"
```
Funcionalidades disponíveis
* all
* consultaPessoaDefault
* consultaCPF
* consultaEmpresaDefault
* expectedErrors
* manualApproval
* matrixReports
* userReport
### Executar os testes com severidade _Blocker_
```text
mvn clean test -DtestGroup="blocker"
```
### Gerar os relatórios do Allure
```text
$ mvn allure:aggregate
```
Os relatórios do Allure são gerados na pasta `target/allure-results`, de acordo com o definido nas configurações do Surefire.

## Resumo da solução desenvolvida para o desafio
* Imagem Docker baseada na _[Official Jenkins Docker image](https://github.com/jenkinsci/docker/blob/master/README.md)_, somente com a modificação do _Dockerfile_ para a geração da imagem com o conteúdo do diretório `/var/jenkins_home`, ao invés de armazenado em um volume.
* Testes desenvolvidos em Java 8, utilizando JUnit 5 e Apache Maven para o build.
* Ferramentas adicionais:
  * [Allure](https://docs.qameta.io/allure/) (para os relatórios de execução dos testes)
  * [Hamcrest](http://hamcrest.org/) (para as asserções)
  * [RestAssured](http://rest-assured.io/) (para o envio e validação das requisições HTTP)
  * [Random Beans](https://github.com/benas/random-beans) (para a geração de valores aleatórios para os dados utilizados nos testes)
  * [Awaitility](https://github.com/awaitility/awaitility) (para a implementação de uma lógica de _polling_ em requisições assíncronas)
* Adicionadas _tags_ às classes e métodos de teste e métodos, para permitir a execução dos testes por linha de comando e definir o grupo de teste que será executado. 
* As classes de teste foram separadas por funcionalidade, e incluídas no pacote `reports`, cumprindo assim aos critérios dos _Cenários não conhecidos_ proposto pelo desafio. Os dados passados a essas classes são informados por parâmetros informados pelas propriedades de execução do Maven / Jenkins, para evitar a exposição de dados sensíveis no código de teste.
* Também incluída a classe de teste `ExpectedErrorsTest.class` para cumprir ao requisito de _Cenários conhecidos_ do desafio proposto.
> _Opinião do autor: os testes relacionados na classe `ExpectedErrorsTest.class`, relacionadas a status HTTP 4xx/5xx, são melhor aplicadas a testes a nível de integração, não a testes a nível de aceite._
* Como os passos, dados e asserções são compartilhados entre as classes de teste, sua implementação está presente dentro do pacote `support`:
  * `ReportsData.class`: contendo os passos necessários para a criação dos dados de teste;
  * `ReportsSteps.class`: contendo os passos para a execução de requisições, asserções de _statusCode_ e tradução do conteúdo das respostas HTTP;
  * `ReportsAssertions.class`: contendo as asserções de acordo com os cenários de teste.
* Criadas classes _DTO_ no pacote `/src/main/dtos/`, para auxiliar na serialização e desserialização de requisições e respostas HTTP, assim como manter as asserções mais legíveis.

## Registro de defeitos
Caso de teste | Resultado esperado | Resultado atual | Severidade
--- | --- | --- | ---
Try get report query with un-existent reportId | Sistema deve retornar o statusCode HTTP 404 (Not Found) | Sistema retornou o statusCode HTTP 200 (OK), com um corpo de resposta sem informação | Normal 
Get person details after request report for matrix consultaCPF | Sistema deve incluir o relatório nos detalhes da pessoa | Relatório não foi incluído na lista de relatórios da pessoa | Normal