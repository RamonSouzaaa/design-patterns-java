# Design Pattern: Builder 🏗️

Este repositório contém a implementação prática do padrão de projeto criacional **Builder** em Java, utilizando como base a entidade `Person`. O objetivo deste projeto é demonstrar como o padrão organiza a construção de objetos complexos com múltiplos atributos opcionais e melhora a legibilidade do código cliente.

---

## 📝 O Conceito

O **Builder** é um padrão de projeto criacional focado em permitir a construção de objetos passo a passo através de uma interface fluida (*Fluent Interface*). 

No desenvolvimento do dia a dia, ele resolve o problema dos chamados **"construtores telescópicos"** — que surgem quando uma classe possui muitos atributos e você é forçado a criar múltiplos construtores ou a passar valores `null` para os campos opcionais no momento da instanciação.

### Quando utilizar?
* Quando sua classe possui atributos obrigatórios misturados com **muitos atributos opcionais** (como `middleName` ou `phoneNumber`).
* Para evitar construtores confusos, onde você pode facilmente trocar a ordem de duas variáveis do tipo `String`.
* Para manter o controle estrito sobre como o objeto nasce, centralizando a lógica de criação em um único método (`build()`).

---

## 🛑 O Problema (Sem o Builder)

Sem o uso do padrão, para criar uma instância de um objeto complexo como `Person`, o código cliente seria obrigado a conhecer a ordem exata de todos os parâmetros do construtor. Se o usuário não possuísse um segundo nome (`middleName`) ou um telefone de contato (`phoneNumber`), o código ficaria poluído com valores nulos:

```java
// Código confuso, sujeito a erros de posição e difícil de ler
Person person = new Person("John", null, "Doe", LocalDate.of(1995, 5, 12), "john.doe@email.com", null);
```

---

## ⚡ A Solução (Com o Builder)

Com o padrão Builder implementado através de uma classe interna estática (`PersonBuilder`), a criação do mesmo objeto se torna uma leitura natural, fluida e encadeada. Você define apenas as informações que possui no momento:

```java
// Código limpo, legível e autoexplicativo
Person person = new Person.PersonBuilder()
    .firstName("John")
    .lastName("Doe")
    .dateOfBirth(LocalDate.of(1995, 5, 12))
    .emailAddress("john.doe@email.com")
    .build(); // O objeto Person é construído e entregue pronto aqui
```

---

## ⚖️ Análise de Vantagens e Desvantagens

### Vantagens:
* **Legibilidade:** Elimina o mistério de saber qual parâmetro `String` pertence a qual campo do construtor no código cliente.
* **Isolamento de Atributos:** Permite criar variações do objeto `Person` sem a necessidade de criar múltiplos construtores sobrecarregados na classe principal.
* **Flexibilidade:** Você pode passar o objeto `PersonBuilder` por diferentes métodos de processamento para preencher os dados aos poucos antes de invocar o `.build()`.

### Desvantagens:
* **Duplicação de Código:** É necessário replicar todos os campos de propriedades da classe `Person` dentro da classe `PersonBuilder`.
* **Verbosidade Inicial:** Exige a escrita de mais linhas de código estrutural para criar os métodos de encadeamento do Builder.

