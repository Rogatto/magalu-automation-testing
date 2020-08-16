# new feature
# Tags: optional

Feature: Fluxo end to end de compra sobre o ecommerce da Magalu

  Scenario: Procurar novo produto
    Given que estou no site da magazine luiza para efetuar nova compra
    When efetuo a busca pelo produto "iPhone 11 Apple 256GB Preto 6,1” 12MP - iOS"
    Then devemos ter o retorno do produto sobre a busca efetuada com seu valor e descrição

  Scenario: Adicionar produto ao carrinho com garantia selecionada
    Given que estamos na tela de consulta da magazine luiza
    When selecionamos o produto "iPhone 11 Apple 256GB Preto 6,1” 12MP - iOS" para adicionar ao carrinho com garantia clicando em continuar
    Then devemos ter as informações do produto vinculados da sacola para a compra

  Scenario: Adicionar produto ao carrinho sem garantia selecionada
    Given que estamos na tela de consulta da magazine luiza para consultar produto
    When selecionamos o produto "iPhone 11 Apple 256GB Preto 6,1” 12MP - iOS" para adicionar ao carrinho sem garantia clicando em continuar
    Then devemos ter as informações do produto vinculados da sacola para a compra sem a garantia selecionada