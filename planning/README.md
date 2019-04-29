# Pontos gerais de melhoria na qualidade de software de um produto
## Atividades iniciais
O envolvimento de um engenheiro de qualidade ocorre desde as primeiras reuniões de concepção do produto, como reuniões de _kick-off_ ou _grooming_. Um defeito pode já ser identificado nestas reuniões, onde uma decisão de arquitetura equivocada ou um requisito mal interpretado podem ser identificados. Um defeito é mais barato de ser corrigido em estágios iniciais do desenvolvimento. 
> Óbvio, não existe uma métrica definitiva sobre o custo do defeito. Vide artigo _[The Tyranny of Always](https://www.developsense.com/blog/2009/08/tyranny-of-always/)_ para detalhes.

A primeira atividade de um engenheiro de qualidade no ciclo de desenvolvimento é a concepção de um documento de estratégia de teste. Dentre as seções deste documento, deve existir uma seção de _exit criteria_, geralmente baseada na quantidade de defeitos abertos x casos de teste. Outros critérios podem ser utilizados, como resultados de testes de desempenho comprovando um _SLA_ satisfatório para a aplicação.

A definição do critério para a finalização dos testes é importante, pois defeitos existem e um software **nunca** está 100% livre deles. Cabe então deixar claro no documento de estratégias de teste qual a métrica que definirá o momento certo para parar de testar.
 
## Planejamento dos testes
Existe um conceito equivocado dentro do mercado de qualidade de software a respeito da utilização de ferramentas de BDD como uma solução para o planejamento e automação de testes, não como uma ferramenta de colaboração entre as equipes de desenvolvimento e de negócio. A escrita dos passos em _feature files_ facilita a criação de cenários de teste com uma descrição legível, porém pode se tornar um problema a médio prazo, pois ao criar uma camada adicional ao _engine_ de teste, passa a ser necessária a manutenção dos _features files_ e da classe de testes, ao invés somente da classe de testes. Utilizar Cucumber / SpecFlow passa a ser justificado quanto _features files_ são artefatos utilizados pela equipe para o desenvolvimento do software e pela equipe de negócios que os utiliza para a criação das histórias e dos cenários de teste (ou critérios de aceite).
> Vide artigo _[The world's most misunderstood collaboration tool](https://cucumber.io/blog/the-worlds-most-misunderstood-collaboration-tool/)_ aonde um dos criadores do Cucumber explica a utilização equivocada da ferramenta como ferramenta de testes

## Execução dos testes e métricas
A execução de testes de aceite automatizados devem ser parte da integração contínua como último passo. Neste momento cabe colocar que os resultados de testes de aceite, sejam eles manuais ou automatizados, não devem ser impeditivos para o _deploy_ em ambiente de produção. Os relatórios de execução de testes devem prover a informação necessária para a tomada de decisão dos envolvidos no desenvolvimento. Algumas da métricas a serem consideradas neste momento são:
* Quantidade de testes executados x Quantidade de testes falhos;
* Quantidade de testes executados x Defeitos encontrados;
* Distribuição dos defeitos encontrados por severidade.

## Automação de testes
Testes automatizados não devem ser somente uma responsabilidade de engenheiros de qualidade, mas sim da equipe de desenvolvimento como um todo. A colaboração de desenvolvedores na automação acontece em maior parte em testes de caixa-branca (unitários e integrados). Os resultados destes testes, em oposição aos resultados de testes de aceite, são *impeditivos* para o deploy. Estes são testes que devem ser levados em consideração em um _pipeline_ de deploy. 

Da mesma maneira, a participação do desenvolvimento nos testes de aceite é essencial, provendo o suporte necessário as engenheiro de qualidade com ferramentas para os testes (caso necessário), e identificando erros provenientes da execução dos testes automatizados.

A linguagem de programação a ser adotada na automação dos testes de aceite deverá seguir a mesma proposta para os testes unitários/integração, para uma maior integração com a equipe de desenvolvimento.

## Conclusão
Existem dois aspectos principais no processo de desenvolmento de software, abordados de maneira indireta nas linhas acima, que devem nortear a qualidade de software:
* Somente existe qualidade naquilo que conseguimos mensurar;
* A comunicação deve ser clara entre os envolvidos no desenvolvimento de software.
