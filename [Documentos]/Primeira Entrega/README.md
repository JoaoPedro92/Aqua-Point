# Relatório Aqua Point

**IADE**

**Engenharia Informática**

Relatório do Projeto Mobile

<p align="center">
  <img src="https://i.imgur.com/wetJpXD.png" />
</p>

**Autores:**

João Rua

Mateus Umba

Evandro Gaspar

**Repositório Git:**

[Aqua-Point: Projeto Mobile - IADE](https://github.com/JoaoPedro92/Aqua-Point)  

**Palavras chave:**

Água, aqua, bebedouro, sede

**Data:** 20 / 09 / 2025

## Índice

[Introdução 5](#_Toc210388509)

[1\. Descrição do problema 6](#_Toc210388510)

[2\. Descrição de objetivos 6](#_Toc210388511)

[3\. Público-Alvo 6](#_Toc210388512)

[4\. Caracterização das ferramentas 7](#_Toc210388513)

[5\. Mapa de Gantt 10](#_Toc210388514)

[6\. Aplicações Similares 11](#_Toc210388515)

[6.1 Onde nos destacamos 11](#_Toc210388516)

[7\. Aqua Point 12](#_Toc210388517)

[7.1 Guiões de teste da aplicação 12](#_Toc210388518)

[8\. Requisitos 15](#_Toc210388519)

[8.1 Requisitos Funcionais 15](#_Toc210388520)

[8.2 Requisitos Não Funcionais 16](#_Toc210388521)

[8.3 Mockups e interfaces 17](#_Toc210388522)

[Conclusão 21](#_Toc210388523)

[Referências bibliográficas 22](#_Toc210388524)

## Índice de figuras

[Figura 1 - Visual Studio Code 7](#_Toc210335715)

[Figura 2 - MAMP 7](#_Toc210335716)

[Figura 3 - GitHub 7](#_Toc210335717)

[Figura 4 - Java 8](#_Toc210335718)

[Figura 5 - Android Studio 8](#_Toc210335719)

[Figura 6 - Workbench 8](#_Toc210335720)

[Figura 7 - MySQL 9](#_Toc210335721)

[Figura 9 - Excel 9](#_Toc210335722)

[Figura 10 - Figma 9](#_Toc210335723)

[Figura 11 - Fases do projeto 10](#_Toc210335724)

[Figura 12 - Mapa de Gantt 10](#_Toc210335725)

[Figura 13 - AquaFinder 11](#_Toc210335726)

[Figura 14 - H20 Quality 11](#_Toc210335727)

## Introdução

**Aqua Point** é uma _aplicação mobile_ cujo permite utilizadores encontrarem bebedouros perto de si, não só para saciarem a sua sede, como também para darem a sua opinião sobre o mesmo.

Com recursos intuitivos e práticos, esta aplicação visa simplificar e otimizar a procura de bebedouros funcionais perto do utilizador.

As Tecnologias utilizadas são bastante variadas, tendo sido utilizados softwares, linguagens de programação e programas de alteração de imagens.

**Softwares:** _Visual Studio Code, Android Studio, GitHub, Figma, MySQL Workbench e MAMP._

**Linguagens de programação**_: Java, MySQL._

**Programas de alteração de imagens:** _Canva, Adobe Photoshop._

O relatório encontra-se organizado pelos seguintes capítulos: descrição do problema, descrição das ferramentas e descrição dos objetivos, motivação, identificação do público alvo, funcionalidades da aplicação, conclusão e bibliografia.

## Descrição do problema

A ideia da aplicação **Aqua Point**, surgiu de um problema, cujo é a falta de bebedouros funcionais perto de cada utilizador.

É muito comum no nosso dia a dia encontrarmos bebedouros que simplesmente não funcionam ou estão em condições muito más.

A criação dessa aplicação visa resolver esses problemas, entregando ao utilizador uma listagem de bebedouros por perto, assim como a apresentação de opiniões e críticas adicionadas por outros utilizadores e ainda a possibilidade de verificar de forma rápida se o bebedouro está funcional ou não.

## Descrição de objetivos

O objetivo desta aplicação é sobretudo fornecer ao utilizador um meio de saciar a sua sede e até mesmo evitar que tenha de fazer caminhadas extensas para chegar a um bebedouro que no final de contas, não funciona ou está em péssimas condições, seja na qualidade da água, ou até mesmo a limpeza do mesmo.

Com o **Aqua Point**, é possível verificar todos os bebedouros por perto, adicionar novos em caso de estarem em falta no mapa, adicionar opiniões relativas ao bebedouro sendo elas em prol da higiene ou até mesmo do funcionamento do dispositivo e além disso, conseguir gerir preferências pessoais no perfil da aplicação.

## Público-Alvo

O público-alvo desta aplicação é destinado desde crianças com 8 anos até a idosos de 60 anos.

Sendo uma app intuitiva e fácil de utilizar, não é necessário ter grande destreza nem experiência com dispositivos mobile.

Além disso, por regra geral, todos nós precisamos de beber água em um dia de calor, esta aplicação é muito importante para essas ocasiões, não só para poupança de tempo, como também para evitar deslocações desnecessárias.

## Caracterização das ferramentas

O tópico seguinte apresenta todas as ferramentas utilizadas ao longo do desenvolvimento do projeto assim como, uma breve descrição relativamente ao funcionamento das mesmas.

| ![Visual Studio Code icon PNG and SVG Vector Free Download](https://i.imgur.com/4ztYo8a.png)<br><br>Figura 1 - Visual Studio Code | O **_Visual Studio Code_** foi utilizado para todo o desenvolvimento da aplicação, sendo ele um editor de código-fonte desenvolvido pela Microsoft para Windows, Linux e macOS. Inclui suporte para depuração, controlo de versionamento Git incorporado, realce de sintaxe, complementação inteligente de código, snippets e refatoração de código. |
| --- | --- |
| ![](https://i.imgur.com/ZDRO8fO.png)<br><br>Figura 2 - MAMP | O **_MAMP_** foi utilizado para criar um servidor local, permitindo a criação de uma base de dados para o projeto.<br><br>**_MAMP_** é um pacote com os principais servidores de código aberto do mercado, incluindo FTP, bases de dados MySQL e Apache com suporte as linguagens PHP e Perl. |
| ![](https://i.imgur.com/eW1hAq9.png)<br><br>Figura 3 - GitHub | O **_GitHub_** foi utilizado para armazenar a solução do projeto e a sua respetiva evolução, sendo ele uma plataforma de hospedagem de código-fonte e ficheiros, com controlo de versão usando o Git. Permite que programadores, utilitários ou qualquer utilizador registado na plataforma contribuam em projetos privados e/ou Open Source de qualquer lugar do mundo. |
| ![](https://i.imgur.com/U7QBYSu.png)<br><br>Figura 4 - Java | A linguagem **_Java_** teve como função toda a criação do back-end deste projeto, mais concretamente para o webservice.<br><br>É uma linguagem de programação compilada, orientada a objectos, de alto nível com tipagem estática e forte. Utiliza a Máquina Virtual Java (**JVM**) para garantir portabilidade e é amplamente usada no desenvolvimento de aplicações empresariais, web e móveis. |
| ![](https://i.imgur.com/je01GVO.png)<br><br>Figura 5 - Android Studio | O **_Android Studio_** foi muito importante para fazer toda a parte visual da nossa aplicação.<br><br>É um ambiente de desenvolvimento integrado (**IDE**) utilizado para criar aplicações Android. Baseado no IntelliJ IDEA, oferece ferramentas para programação, interface gráfica, depuração e testes, sendo o ambiente oficial recomendado pelo Google. |
| ![](https://i.imgur.com/mz18V80.png)<br><br>Figura 6 - Workbench | O **_CSS_** teve como função toda a parte estética do _website_.<br><br>**MySQL Workbench** é uma aplicação gráfica para trabalhar com bases de dados MySQL, permitindo criar e modelar esquemas, executar consultas SQL e administrar servidores. Também possibilita a concepção visual de tabelas e relações, facilitando a gestão e o desenvolvimento. |
| ![](https://i.imgur.com/nNkgeTY.png)<br><br>Figura 7 - MySQL | A utilização de **My_SQL_** é fundamental permitindo o armazenamento de todas as informações de utilizadores e bebedouros<br><br>**MySQL** é um sistema de gestão de bases de dados relacionais de código aberto, baseado em **SQL**, desenvolvido para armazenar, organizar e consultar grandes volumes de informação. É amplamente utilizado em aplicações web, serviços empresariais e plataformas que exigem desempenho e escalabilidade. |
| ![Uma imagem com captura de ecrã, símbolo, Saturação de cores, quadrado<br><br>Descrição gerada automaticamente](https://i.imgur.com/jInp9AU.png)<br><br>Figura 9 - Excel | O **_Excel_** Foi utilizado para a criação do Mapa de Gantt.<br><br>É um editor de planilhas produzido pela Microsoft para computadores que utilizam o sistema operacional Microsoft Windows, além de computadores Macintosh da Apple Inc. e dispositivos móveis como o Windows Phone, Android ou o iOS. |
| ![](https://i.imgur.com/oNgv7SH.png)<br><br>Figura 10 - Figma | O **_Figma_** foi utilizado para a criação de toda a parte gráfia a aplicação em mockup, sendo o responsável por toda a base ideológica gráfica do projeto.<br><br>**Figma** é uma ferramenta de design de interfaces, utilizada para criar layouts e protótipos interativos. Permite colaboração em tempo real entre equipas, facilitando o desenvolvimento de projetos de design de forma eficiente. |

## Mapa de Gantt

As duas imagens abaixo representam o **Mapa de Gantt** do projeto **Aqua Point,** onde constam as respetivas fases e duração das mesmas.

<p align="center">
  <img src="https://i.imgur.com/BFXroPh.png" />
</p>

Figura 11 - Fases do projeto

Na figura seguinte, é apresentado o cronograma do projeto seguindo a planificação do Mapa de Gantt.

<p align="center">
  <img src="https://i.imgur.com/HUWpDB6.png" />
</p>

Figura 12 - Mapa de Gantt

## Aplicações Similares

**AquaFinder**: Uma aplicação colaborativa que mostra um mapa com pontos onde é possível encher a garrafa, muitas vezes incluindo bebedouros públicos e pontos de água em cidades de todo o mundo.

![](https://i.imgur.com/06iy6Nh.png)

Figura 13 - AquaFinder

**H20 QUALITY**: Desenvolvida pela EPAL (Empresa Portuguesa das Águas Livres, SA), esta aplicação é específica para encontrar bebedouros da EPAL em Lisboa e permite consultar a qualidade da água.

![](https://i.imgur.com/otllvuD.png)

Figura 14 - H20 Quality

### Onde nos destacamos

A aplicação Aqua Point oferece uma solução mais robusta quando comparada a outras soluções disponíveis no mercado.

Além de oferecer a funcionalidade base de localizar bebedouros, permite ainda gerir a área pessoal de cada utilizador, avaliar individualmente em diferentes aspetos cada bebedouro assim como marcá-lo como avariado ou fora de serviço, algo que muitos aplicativos similares não oferecem.

## Aqua Point

Nos tópicos seguintes, é apresentada a estrutura da _aplicação,_ desde a sua base de dados, parte visual e pequenas partes de código.

### Guiões de teste da aplicação
- **Caso de Utilização 1**
```
Objetivo: 
Permitir que o utilizador encontre rapidamente o bebedouro mais próximo.

Condições:
O utilizador tem a app instalada e está com GPS ativo.
Existe ligação à internet.

Passo-a-passo:
- O utilizador abre a aplicação.
- A aplicação pede permissão para aceder à localização do dispositivo.
- O utilizador aceita o pedido de permissão.
- A app mostra no mapa a localização do utilizador.
- A app procura bebedouros próximos e exibe os respetivos ícones no mapa.
- O utilizador toca no ícone de um bebedouro.
- A app mostra detalhes (localização, distância e opiniões de outros utilizadores).
- O utilizador clica em "Obter Direções".
- A app abre o trajeto no mapa.
```

- **Caso de Utilização 2**
```
Objetivo: 
Permitir que o utilizador partilhe a sua opinião relativamente a um bebedouro

Condições:

O utilizador tem a app instalada e está com GPS ativo.
Existe ligação à internet.

Passo-a-passo:
- O utilizador abre a aplicação.
- A app mostra no mapa a localização do utilizador.
- A app procura bebedouros próximos e exibe os respetivos ícones no mapa.
- O utilizador toca no ícone de um bebedouro.
- A app mostra detalhes (localização, distância e opiniões de outros utilizadores).
- O utilizador clica em "Dar opinião".
- É dada a possibilidade ao utilizador de avaliar mediante o número de estrelas ( 1 a 5 ), adicionar um comentário e ainda marcar o bebedouro como avariado.
- A aplicação guarda a opinião do utilizador, recalculando a média de avaliação do bebedouro.
- O utilizador vê a sua opinião refletida na aplicação em tempo real.
```

- **Caso de Utilização 3**
```
Objetivo: 
Permitir que os utilizadores ajudem a manter a base de dados atualizada.

Condições:
O utilizador tem a app instalada e está com GPS ativo.
Existe ligação à internet.
O utilizador tem de ter histórico de avaliações / utilização da app antes de adicionar novos bebedouros.

Passo-a-passo:
- O utilizador vai até ao bebedouro.
- Abre a aplicação.
- A app procura bebedouros próximos e exibe os respetivos ícones no mapa.
- O utilizador toca na funcionalidade de "Adicionar Bebedouro".
- Um novo menu aparece com objetivo de pedir informações ao utilizador sobre o novo bebedouro.
- Utilizador clica no botão de "Submeter".
- O bebedouro é adicionado ao mapa com base na localização atual do utilizador.
- O utilizador vê as suas ações refletidas em tempo real.
```
- **Caso de Utilização 4**
```
Objetivo: 
Fazer registo na aplicação.

Condições:
O utilizador tem a app instalada.
Existe ligação à internet.

Passo-a-passo:
- O utilizador deve abrir a aplicação.
- Clicar no ícone de perfil.
- Clicar no botão de "Registar".
- Preencher os campos com as suas informações.
```
- **Caso de Utilização 5**
```
Objetivo: 
Fazer login na aplicação.

Condições:
O utilizador tem a app instalada.
O utilizador deve ter uma conta registada na aplicação.
Existe ligação à internet.

Passo-a-passo:
- O utilizador deve abrir a aplicação.
- Clicar no ícone de perfil.
- Clicar no botão de "Login".
- Preencher os campos com as suas informações.
- Requisitos
  - Requisitos Funcionais
```

## Requisitos funcionais:
Com base nos objetivos do sistema, foram definidos os seguintes requisitos funcionais:

**RF1 - Localização de bebedouros**

- O sistema deve mostrar, em um mapa interativo, os bebedouros próximos ao utilizador, com base na sua localização via GPS.
- Fazer com que o utilizador consiga obter direções até ao bebedouro selecionado.

**RF2 - Avaliação e feedback**

- Permitir que os utilizadores adicionem opiniões, comentários e classificações (de 1 a 5 estrelas) sobre os bebedouros.
- Permitir que um utilizador marque um bebedouro como avariado ou em más condições.

**RF3 - Gestão de bebedouros**

- Possibilitar a adição de novos bebedouros ao mapa.
- Atualizar em tempo real a base de dados com informações fornecidas pelos utilizadores.

**RF4 - Autenticação e perfis**

- Permitir que o utilizador crie uma conta (registo) e autentique-se (login).
- O sistema deve permitir a gestão de preferências no perfil do utilizador.

**RF5 - Integração com mapas**

- Apresentar trajetos até ao bebedouro escolhido, integrando-se com serviços de mapas.

**RF6 - Interface intuitiva**

- O sistema deve disponibilizar ecrãs de boas-vindas, login, registo, página inicial (_home_) e pesquisa.
  - Requisitos Não Funcionais

**RNF1 - Usabilidade**

- A interface deve ser simples e intuitiva, adequada para utilizadores entre 8 e 60 anos.
- A navegação deve ser rápida e clara, com ícones facilmente identificáveis.

**RNF2 - Desempenho**

- Responder em tempo real à pesquisa de bebedouros.
- O carregamento do mapa e dos dados deve ocorrer de forma rápida.

**RNF3 - Confiabilidade**

- Garantir que os dados inseridos pelos utilizadores (avaliações e novos bebedouros) sejam armazenados corretamente.
- Apresentar disponibilidade estável em condições normais de uso.

**RNF4 - Segurança**

- Os dados dos utilizadores (login e perfil) devem ser armazenados de forma segura.
- Deve respeitar as permissões de acesso ao GPS e aos dados pessoais.

**RNF5 - Compatibilidade**

- A aplicação deve funcionar em dispositivos Android, com possibilidade futura de expansão para iOS.

**RNF6 - Escalabilidade**

- A base de dados deve ser capaz de suportar um número crescente de utilizadores e bebedouros.
- Mockups e interfaces

## Mockups e interfaces
O tópico a seguir apresenta alguns ecrãs que formam a estrutura da aplicação, organizada por meio de mockups que ilustram as suas funcionalidades:

**Ecrã de Boas-Vindas**

![](https://i.imgur.com/QKB7Gsv.png)

**Ecrã de Registo**

![](https://i.imgur.com/J9qSgi5.png)

**Ecrã de Login**

![](https://i.imgur.com/X1PGOfE.png)

**Ecrã Home**

![](https://i.imgur.com/5tGp7Xt.png)

**Ecrã Home after selct**

![](https://i.imgur.com/8GB1S1Y.png)

**Ecrã de Pesquisa**

![](https://i.imgur.com/Wi03vyD.png)

<p align="center">
  <img src="https://i.imgur.com/u0VHiYr.png" />
</p>

## Conclusão

O projeto **Aqua Point** encontra-se atualmente em fase de desenvolvimento, representando um desafio significativo no âmbito da engenharia informática e do desenvolvimento de aplicações móveis. Nesta primeira etapa é feito o planeamento e estruturação do projeto, delineando os seus fundamentos, objetivos e a abordagem técnica a ser adotada.

O principal objetivo a atingir com este trabalho é a criação de uma aplicação móvel funcional e intuitiva que permita aos utilizadores localizar bebedouros próximos, avaliar a sua condição e partilhar informações em tempo real. A aplicação visa resolver um problema quotidiano - a dificuldade em encontrar pontos de água potável funcionais e em condições de utilização.

Este relatório serve como um guia estratégico e um ponto de partida para o desenvolvimento do **Aqua Point**. Os próximos passos envolverão a execução prática deste planeamento, superando os desafios técnicos e conceptuais identificados, com o objetivo final de entregar uma aplicação de valor para a comunidade.

## Referências bibliográficas

Google. (20 de 09 de 2025). _Android Studio_. Obtido de Android Studio: <https://developer.android.com/studio?hl=pt-br>

_MAMP PRO & MAMP_. (20 de 09 de 2025). Obtido de MAMP PRO & MAMP: <https://www.mamp.info/en/windows/>

_Visual Studio Code_. (20 de 09 de 2025). Obtido de Visual Studio Code: <https://azure.microsoft.com/pt-pt/products/visual-studio-code>

Wikipédia. (20 de 09 de 2025). _Excel_. Obtido de Excel: <https://pt.wikipedia.org/wiki/Microsoft_Excel>

Wikipédia. (20 de 09 de 2025). _Figma_. Obtido de Figma: <https://en.wikipedia.org/wiki/Figma>

Wikipédia. (20 de 09 de 2025). _GitHub_. Obtido de GitHub: <https://en.wikipedia.org/wiki/GitHub>

Wikipédia. (20 de 09 de 2025). _Java_. Obtido de Java: <https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)>

Wikipédia. (20 de 09 de 2025). _Mysql Workbench_. Obtido de Mysql Workbench: <https://en.wikipedia.org/wiki/MySQL_Workbench>