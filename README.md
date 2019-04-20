# Startup - Lanche

Essa aplicação tem como objetivos realizar vendas de lanches para uma startup onde o valor do lanche é dado pela somas dos ingredientes, podendo escolher lanches do cardápio ou personalizar seu lanche.
Existe uma exceção à regra para o cálculo de preço, quando o lanche pertencer à uma promoção. A seguir, apresentamos a lista de promoções e suas respectivas regras de negócio:


| Promoção  |   Regra de negócio |
| ------------ | ------------ |
|Light   |  Se o lanche tem alface e não tem bacon, ganha 10% de desconto. |
|Muita carne   |  A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante... |
| Muito queijo  |  A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante... |

## Tecnologias Front-end
- Html
- Javascript
- Css
- Materialize

## Tecnologias Back-end
- Java 8
- Spring Boot
- H2 Database
- Maven
- JPA
- JUnit

## Justificativa da escolha do design de código
Foi escolhido seguir o padrão REST API pois devido a sua flexibilidade em se trabalhar com diferentes chamadas de dispositivos atualmente o front está feito em html, css, javascript mas se em um futuro quiser interagir com um aplicativo mobile fica fácil, também esse tipo de arquitetura traz um controle maior do que se está proposto a fazer.

## Executar Aplicação
1.  Baixe o projeto
2. Tenha docker instalado em sua máquina
3. abra o terminal de comando digite **docker run -p 8080:8080 capp59marcelo/dextra:1.0**
4. Esse projeto roda em http://localhost:8080/index.html utilizar os browsers firefox ou chrome mais atualizados.


