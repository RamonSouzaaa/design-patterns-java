# Design Pattern: Prototype (Implementação Manual) 👥

Este repositório contém a implementação prática do padrão de projeto criacional **Prototype** em Java. O diferencial deste projeto é a aplicação da **clonagem manual (sem o uso da interface nativa `Cloneable`)**, utilizando uma abordagem de arquitetura limpa através de construtores de cópia e métodos customizados, eliminando as limitações técnicas e exceções checadas da API nativa do Java.

---

## 📝 O Conceito

O **Prototype** é um padrão de projeto criacional que permite copiar (clonar) objetos existentes sem que o seu código principal precise depender das classes reais desses objetos ou expor seus atributos privados para o mundo externo.

### Por que evitar o `Cloneable` nativo do Java?
Muitos especialistas em Java (incluindo Joshua Bloch, autor de *Java Efetivo*) recomendam fortemente criar a clonagem manualmente em vez de usar o `Cloneable` nativo pelos seguintes motivos:
1. **Interface de Marcador Falha:** A interface `Cloneable` não possui o método `clone()`. O método pertence à classe base `Object`, o que quebra a semântica normal de interfaces em Java.
2. **Exceções Inconvenientes:** Obriga o código a tratar a exceção checada `CloneNotSupportedException`.
3. **Ignora o Construtor:** O `super.clone()` cria o objeto fazendo uma cópia bit a bit diretamente na memória, contornando o construtor da classe e abrindo brechas de segurança.

---

## 🛑 O Problema (Sem o Prototype)

Se você precisar de uma cópia idêntica de um objeto que possui atributos privados (`private`), e tentar fazer isso de fora da classe, você será obrigado a expor todos os atributos via métodos `get` e reinjetá-los via métodos `set` ou via construtor público no novo objeto. 

Se a classe tiver campos estritamente privados e encapsulados (sem Getters), a clonagem externa se torna matematicamente impossível.

---

## ⚡ A Solução Elegante: Clonagem Manual

A melhor maneira de resolver isso manualmente é combinando um **Contrato de Cópia** (uma interface customizada) com um **Construtor de Cópia** (*Copy Constructor*). Dessa forma, a própria classe recebe uma instância de si mesma e copia seus campos internos com total segurança de tipos e sem nenhuma exceção.

---

## 🛠️ Implementação Prática: Cenário de Células de Planilha

Imagine um sistema de planilhas onde o usuário pode copiar e colar células. Criar uma célula do zero exige calcular fórmulas e estilos pesados. Usar o Prototype manual torna a ação de "copiar e colar" extremamente limpa.

### 1. O Nosso Contrato (Interface Personalizada)
Criamos a nossa própria interface para ditar a regra de clonagem de forma explícita e tipada:

```java
package br.com.criacionais.prototype;

public interface Prototype<T> {
    T duplicar(); // Substitui o clone() nativo sem gerar exceções
}
```

### 2. A Classe Concreta com Construtor de Cópia (`Cell`)

```java
package br.com.criacionais.prototype;

public class Cell implements Prototype<Cell> {
    private String content;
    private String backgroundColor;
    private int borderSize;

    // Construtor Padrão (Usado para criar o primeiro objeto do zero)
    public Cell(String content, String backgroundColor, int borderSize) {
        this.content = content;
        this.backgroundColor = backgroundColor;
        this.borderSize = borderSize;
    }

    // --- CONSTRUTOR DE CÓPIA (O Segredo da Clonagem Manual) ---
    // Ele recebe um objeto do mesmo tipo e copia todos os atributos diretamente internamente
    public Cell(Cell original) {
        this.content = original.content;
        this.backgroundColor = original.backgroundColor;
        this.borderSize = original.borderSize;
    }

    // Getters e Setters para permitir alterações pós-clonagem
    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }

    @Override
    public String toString() {
        return "Cell{" + "content='" + content + '\'' + ", bg='" + backgroundColor + '\'' + ", border=" + borderSize + '}';
    }

    // --- IMPLEMENTAÇÃO DO PROTOTYPE ---
    @Override
    public Cell duplicar() {
        // Invoca o construtor de cópia de forma limpa, segura e tipada
        return new Cell(this); 
    }
}
```

### 3. O Código Cliente Utilizando a Clonagem Manual

```java
package br.com.criacionais.prototype;

public class Main {
    public static void main(String[] args) {
        // 1. Criamos a primeira célula (O Protótipo) com estilo configurado
        Cell celulaOriginal = new Cell("Dados Anuais", "#FFFFFF", 2);
        System.out.println("Original: " + celulaOriginal);

        // 2. O usuário clona a célula através do nosso método manual (Ação de copiar e colar)
        Cell celulaClonada = celulaOriginal.duplicar();
        System.out.println("Clone idêntico: " + celulaClonada);

        // 3. Modificamos apenas o conteúdo do clone. O estilo visual permanece intacto!
        celulaClonada.setContent("Dados Mensais");
        
        System.out.println("\n--- Após a modificação do clone ---");
        System.out.println("Original continua intocado: " + celulaOriginal);
        System.out.println("Clone modificado com sucesso: " + celulaClonada);
        
        // Prova de que são instâncias independentes na memória (Retorna 'false')
        System.out.println("\nSão o mesmo objeto na memória? " + (celulaOriginal == celulaClonada));
    }
}
```

---

## ⚖️ Análise de Vantagens e Desvantagens da Abordagem Manual

### Vantagens:
* **Segurança de Compilação:** Não há conversões de tipo forçadas (type casting) ou exceções checadas em tempo de execução.
* **Respeito ao Ciclo de Vida:** O construtor da classe (`new Cell(...)`) é acionado de verdade, garantindo que qualquer regra de inicialização de objeto seja executada.
* **Facilidade para Deep Copy (Cópia Profunda):** Se a célula tivesse uma lista interna, bastaria dar um `new ArrayList<>(original.lista)` dentro do construtor de cópia. É extremamente intuitivo de manter.

### Desvantagens:
* **Dependência Visual de Código:** Exige que você escreva manualmente o construtor de cópia para cada nova propriedade adicionada à classe, embora isso evite bugs de referências compartilhadas indesejadas.
