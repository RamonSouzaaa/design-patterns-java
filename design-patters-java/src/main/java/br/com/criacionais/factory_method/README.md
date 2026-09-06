# Design Pattern: Factory Method 🎫

Este repositório contém a implementação prática do padrão de projeto criacional **Factory Method** em Java. O projeto simula um sistema de bilheteria urbana (*Ticket System*), demonstrando como o padrão separa a lógica de negócio comum das regras de preço específicas de cada tipo de bilhete, eliminando acoplamentos diretos e blocos de decisão (`if/else` ou `switch`).

---

## 📝 O Conceito

O **Factory Method** é um padrão de projeto criacional que define uma interface ou classe abstrata para criar um objeto, mas deixa que as subclasses decidam qual classe concreta instanciar. Ele permite que um sistema delegue a responsabilidade de criação para classes especializadas, promovendo o desacoplamento e respeitando o **Princípio do Aberto/Fechado (OCP)** do SOLID.

### Quando utilizar?
* Quando o sistema não sabe antecipadamente o tipo exato do objeto que precisará criar.
* Quando uma classe mãe quer que suas subclasses forneçam a versão específica do objeto que será criado.
* Para centralizar a lógica de criação de uma família de produtos, permitindo a expansão para novos tipos sem modificar o código existente.

---

## ⚡ A Arquitetura do Exemplo

Dividimos a arquitetura em duas hierarquias paralelas: a dos **Produtos** (`Ticket`) e a das **Fábricas** (`TicketMachine`). 

A classe base `Ticket` possui o comportamento compartilhado de compra (`buyTicket()`), mas delega o valor real da passagem para cada implementação através do método abstrato `getPrice()`.

---

## 🛠️ Implementação do Código em Java

Abaixo está a estrutura completa do padrão mapeada para o cenário de transportes dentro do pacote `br.com.criacionais.factory`:

### 1. A Estrutura dos Produtos (`Ticket`)

```java
package br.com.criacionais.factory;

public abstract class Ticket {
    
    // Método abstrato: cada filho determina seu próprio valor regulamentado
    protected abstract double getPrice();
    
    // Comportamento comum herdado por todos os tipos de bilhetes
    public void buyTicket() {
        System.out.println("Ticket buyed.. Price: \$" + getPrice());
    }
}
```

```java
package br.com.criacionais.factory;

// Produto Concreto 1: Ônibus
public class BusTicket extends Ticket {
    @Override
    protected double getPrice() {
        return 4.50; // Preço específico do ônibus
    }
}
```

```java
package br.com.criacionais.factory;

// Produto Concreto 2: Trem
public class TrainTicket extends Ticket {
    @Override
    protected double getPrice() {
        return 6.20; // Preço específico do trem
    }
}
```

### 2. A Estrutura das Fábricas (`TicketMachine`)

```java
package br.com.criacionais.factory;

// O Contrato da Fábrica (Define a assinatura do Factory Method)
public interface TicketMachine {
    Ticket createTicket(); // O Factory Method original
}
```

```java
package br.com.criacionais.factory;

// Fábrica Concreta 1: Terminal de Ônibus
public class BusTicketMachine implements TicketMachine {
    @Override
    public Ticket createTicket() {
        return new BusTicket(); // Instancia e entrega o produto de ônibus
    }
}
```

```java
package br.com.criacionais.factory;

// Fábrica Concreta 2: Estação de Trem
public class TrainTicketMachine implements TicketMachine {
    @Override
    public Ticket createTicket() {
        return new TrainTicket(); // Instancia e entrega o produto de trem
    }
}
```

---

## ⚡ Como o Código Cliente Utiliza a Estrutura

No ponto de execução do sistema, a escolha de qual máquina utilizar é definida de forma global (seja por configuração do terminal físico, variáveis de ambiente ou injeção de dependência). Depois disso, a emissão e a compra fluem de forma totalmente polimórfica:

```java
package br.com.criacionais.factory;

public class Main {
    public static void main(String[] args) {
        // 1. O sistema inicializa configurando qual máquina este terminal físico possui
        // Se mudarmos para new BusTicketMachine(), o restante do código permanece intocado
        TicketMachine maquinaDoTerminal = new TrainTicketMachine();

        // 2. O passageiro solicita um bilhete. O código depende apenas da Interface da Fábrica
        Ticket passaporteDoUsuario = maquinaDoTerminal.createTicket();

        // 3. O bilhete executa o comportamento herdado utilizando o preço específico da subclasse
        passaporteDoUsuario.buyTicket(); 
        // Saída: "Ticket buyed.. Price: \$6.2"
    }
}
```

---

## ⚖️ Análise de Vantagens e Desvantagens

### Vantagens:
* **Extensibilidade Infinita (OCP):** Se amanhã a cidade implantar o **Metrô (SubwayTicket)**, basta criar as novas classes do produto e de sua respectiva máquina. O código existente permanece intacto e protegido de bugs colaterais.
* **Responsabilidade Única (SRP):** A classe cliente que gerencia a catraca ou o fluxo de pagamento não precisa carregar o conhecimento técnico de como instanciar ou configurar cada tipo de bilhete.
* **Reuso de Código:** Comportamentos genéricos (como o fluxo base de compra `buyTicket()`) ficam concentrados na classe mãe, eliminando duplicidade de código.

### Desvantagens:
* **Proliferação de Arquivos:** O padrão exige a criação de duas novas classes (Produto e Fábrica) para cada nova modalidade de transporte adicionada ao sistema, aumentando a verbosidade inicial do projeto.