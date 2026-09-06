# Design Pattern: Abstract Factory & Factory Method (Unified Design) 🚲

Este repositório contém a implementação prática combinando os padrões de projeto criacionais **Abstract Factory** e **Factory Method** em Java. O projeto simula uma linha de produção automatizada de bicicletas (*Bike Manufacturing System*), demonstrando como esses dois padrões trabalham em perfeita harmonia para eliminar estruturas condicionais (`if/else` ou `switch`) e garantir a consistência na criação de objetos.

---

## 📝 A Análise Arquitetural

No desenvolvimento real de software, o **Abstract Factory** e o **Factory Method** frequentemente operam juntos:
1. **Abstract Factory (A Interface Mãe):** Define o contrato macro de uma linha de produção ou "tema" (ex: Linha Mountain vs. Linha Road).
2. **Factory Method (A Engrenagem Interna):** Cada método de criação declarado dentro da fábrica abstrata (como o `createBike()`) opera polimorficamente como um Factory Method, onde a subclasse concreta decide qual `new` executar.

### O foco do Abstract Factory: Famílias de Produtos
O padrão se consagra como uma *Abstract Factory* completa à medida que o sistema cresce e a fábrica passa a gerenciar não apenas um produto isolado, mas uma **família de produtos relacionados** que precisam combinar obrigatoriamente entre si (ex: a bicicleta, o pneu correto e o guidão correto), impedindo o cliente de misturar componentes incompatíveis.

---

## 📋 Mapeamento de Classes e Responsabilidades

Para entender a dinâmica do projeto, abaixo estão listadas as classes implementadas e o papel exato de cada uma na arquitetura do sistema:

### Contratos / Abstrações:
* **`Bike` (Classe Abstrata):** Define o esqueleto de comportamento do produto principal (o veículo).
* **`Pneu` (Interface):** Define o contrato para os componentes adicionais que farão parte da família da bicicleta.
* **`BikeFactory` (Interface - A Abstract Factory):** Define as assinaturas dos métodos de criação. Ela dita quais peças devem ser criadas juntas para compor o "kit" correto, agindo como o esqueleto das fábricas.

### Implementações da Linha Mountain (Trilha):
* **`MountainBike` (Classe):** Implementação real do veículo adaptada para terrenos irregulares.
* **`PneuGrosso` (Classe):** Componente de pneu robusto e com cravos específico para tração na terra.
* **`MountainFactory` (Classe - Fábrica Concreta):** Responsável por implementar os métodos da interface mãe, garantindo o encapsulamento e a instância exclusiva do kit de peças da linha Mountain.

### Implementações da Linha Road (Asfalto):
* **`RoadBike` (Classe):** Implementação real do veículo focada em velocidade e aerodinâmica no asfalto.
* **`PneuFino` (Classe):** Componente de pneu liso e estreito calibrado para alta pressão e menor atrito.
* **`RoadFactory` (Classe - Fábrica Concreta):** Responsável por implementar os métodos da interface mãe, garantindo o encapsulamento e a instância exclusiva do kit de peças da linha Road.

---

## 🛠️ Implementação do Código em Java

Abaixo está o código-fonte estruturado dentro do pacote `br.com.criacionais.abstractfactory`:

### 1. Os Contratos e Produtos da Família (`Bike` e `Pneu`)

```java
package br.com.criacionais.abstractfactory;

// Abstração do Produto Principal
public abstract class Bike {
    public abstract void andar();
}

// Abstração do Componente Relacionado
public interface Pneu {
    void calibrar();
}
```

```java
package br.com.criacionais.abstractfactory;

// Produtos Concretos da Linha Mountain (Trilha)
public class MountainBike extends Bike {
    @Override
    public void andar() { System.out.println("Encarando a terra com suspensão ativa!"); }
}

public class PneuGrosso implements Pneu {
    @Override
    public void calibrar() { System.out.println("Calibrando pneu grosso de cravo para trilha."); }
}
```

```java
package br.com.criacionais.abstractfactory;

// Produtos Concretos da Linha Road (Asfalto)
public class RoadBike extends Bike {
    @Override
    public void andar() { System.out.println("Voando no asfalto liso com aerodinâmica!"); }
}

public class PneuFino implements Pneu {
    @Override
    public void calibrar() { System.out.println("Calibrando pneu fino de alta pressão para velocidade."); }
}
```

### 2. A Estrutura das Fábricas Combinadas (`BikeFactory`)

```java
package br.com.criacionais.abstractfactory;

// A Abstract Factory declarando os Factory Methods da família de produtos
public interface BikeFactory {
    Bike createBike(); // Factory Method 1
    Pneu createPneu(); // Factory Method 2
}
```

```java
package br.com.criacionais.abstractfactory;

// Fábrica Concreta especializada na linha Mountain
public class MountainFactory implements BikeFactory {
    @Override
    public Bike createBike() { return new MountainBike(); }

    @Override
    public Pneu createPneu() { return new PneuGrosso(); } // Garante a consistência do kit
}
```

```java
package br.com.criacionais.abstractfactory;

// Fábrica Concreta especializada na linha Road
public class RoadFactory implements BikeFactory {
    @Override
    public Bike createBike() { return new RoadBike(); }

    @Override
    public Pneu createPneu() { return new PneuFino(); } // Garante a consistência do kit
}
```

---

## ⚡ Utilização no Código Cliente

A escolha de qual ecossistema/tema produzir ocorre em uma única linha do sistema (geralmente vinda de configurações ou Injeção de Dependência). Todo o restante do software consome a fábrica e as peças de forma puramente polimórfica e segura:

```java
package br.com.criacionais.abstractfactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Configurando Linha de Produção Atual ---");

        // 1. Definimos o ecossistema global desejado (Apenas alterando a fábrica concreta)
        BikeFactory fabricaDeTurno = new MountainFactory();

        // 2. Os Factory Methods entregam os produtos alinhados sem o uso de strings ou enums
        Bike bikeMontada = fabricaDeTurno.createBike();
        Pneu pneuMontado = fabricaDeTurno.createPneu();

        // 3. Execução das regras de negócio de forma desacoplada
        bikeMontada.andar();    // Saída: "Encarando a terra com suspensão ativa!"
        pneuMontado.calibrar(); // Saída: "Calibrando pneu grosso de cravo..."
    }
}
```

---

## ⚖️ Conclusão da Análise de Design

* **Por que este design é robusto?** Ele blinda o código cliente contra erros humanos. É matematicamente impossível o desenvolvedor instanciar por engano uma `RoadBike` com um `PneuGrosso` de trilha, porque a `MountainFactory` encapsula e amarra as criações corretas da mesma família.
* **Aderência ao SOLID:** O código cumpre perfeitamente o **OCP** (Princípio do Aberto/Fechado), pois adicionar novos modelos de bicicletas (como *Bikes Elétricas*) exige apenas adicionar novos arquivos de classes, sem alterar nenhuma linha do sistema de montagem atual.
