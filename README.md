# magazineluiza ecommerce automation testing

> Para garantirmos a qualidade sobre a plataforma de ecommerce da Magazine Luiza criamos uma estrutura utilizando projeto Maven com Java, Selenium WebDriver, Junit, Cucumber, com sua execução vinculada ao Zalenium (temos a necessidade da instalação do docker em nosso sistema operacional) e a geração do relatório customizado sobre a execução da fearures

> Utilizamos para estruturar nosso projeto de automação o designer pattern de page objects sobre as páginas da Magalu.


# Instalação do Zalenium (para efetuar tais comandos precisamos do docker instalado em nosso sistema operacional)

```sh
$ docker pull elgalu/selenium
```

```sh
$ docker pull dosel/zalenium
```

# Execução do Zalenium

**Executar container local para apontar nossos testes**


```sh
$  docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start
```
   
**Link Live Zalenium:**
http://localhost:4444/grid/admin/live

**Link Dashboard Zalenium:**
http://localhost:4444/dashboard/

## Estrutura do projeto

O projeto foi estruturado da seguinte maneira:

```
src
    \main
        \java
            # Pasta onde contém todos os page objects e métodos relacionados ao site da Magalu
            \magalu.pageobjects
            # Pasta onde contém todos os utilitários do projeto.
            \utils
    \test
        \java
            # Pasta onde contém os testes de todas as funcionalides da Magalu.
            \features
            # Pasta onde contém os executores de funcionalidades da Magalu.
            \runners
            # Pasta onde contém todos os passados de execução e asserção.
            \steps
```

## Para execução de nossa aplicação/automação:

```sh
$ mvn clean install
```

## Diretório com os relatórios sobre a execução com todas evidências
```
magalu-automation-testing
    \target
        \generated-report
            # diretório dos relatórios sobre a automação
            \index.html
```

## Diretório com as evidências da execução
```
magalu-automation-testing
    \evidences
            # diretório das evidências geradas sobre a automação
            productFounded.png
            productAddedWithAssurance.png
            productAddedWithoutAssurance.png
```

#### Estamos utilizando o Git Actions para a continuos delivery para todo deployment em master desse repositório
```
magalu-automation-testing
    \.github
            # Git Actions
```