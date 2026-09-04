# Design Pattern: Singleton (via Enum) 🎯

Este repositório contém a implementação prática do padrão de projeto criacional **Singleton** em Java, utilizando uma abordagem moderna, eficiente e robusta baseada em `enum`. O exemplo utiliza o cenário de um gerenciador de impressão (`Printer`).

---

## 📝 O Conceito

O **Singleton** é um padrão de projeto criacional cujo objetivo principal é garantir que uma classe tenha **apenas uma única instância** em toda a execução do aplicativo, fornecendo um ponto global de acesso a ela.

No desenvolvimento de software, ele é comumente utilizado para gerenciar recursos compartilhados que possuem um custo alto de criação ou que precisam centralizar ações, tais como:
* Gerenciadores de conexões com bancos de dados.
* Componentes de Log do sistema.
* Controladores de acesso a hardwares compartilhados (como uma impressora física).

### Por que usar Enum para criar Singletons?
Segundo Joshua Bloch (autor do livro *Java Efetivo*), o uso de `enum` é a **melhor maneira de implementar o Singleton em Java**. Essa abordagem resolve nativamente problemas complexos de outras implementações tradicionais:
1. **Thread-Safe Nativo:** O Java garante que a instância do enum seja criada de forma segura mesmo se múltiplas threads tentarem acessá-la simultaneamente durante a inicialização.
2. **Proteção contra Reflexão (Reflection):** Impede que desenvolvedores maliciosos ou desavisados quebrem o Singleton forçando a criação de uma segunda instância via Java Reflection API.
3. **Imutabilidade do Ciclo de Vida:** A instância é criada apenas **uma vez** pelo ClassLoader do Java quando a classe é carregada na memória e reutilizada em todas as chamadas subsequentes.

---

## 🛠️ Implementação do Código

Abaixo está o código-fonte estruturado do padrão dentro do pacote `br.com.criacionais.singleton`:

```java
package br.com.criacionais.singleton;

public enum Printer {
    
    // O único objeto real e global vivo na memória do sistema
    INSTANCE;
    
    // Construtor privado: impede que qualquer código externo dê "new Printer()"
    private Printer() {}
    
    // Ponto de acesso global estático para recuperar a instância única
    public static Printer getInstance() {
        return INSTANCE;
    }
    
    // Método de negócio (Comportamento do objeto)
    public void print() {
        System.out.println("Printing..");
    }
}
```

---

## ⚡ Como utilizar o Singleton no Código Cliente

Uma confusão comum ao utilizar Enums como Singleton é como invocar seus métodos. Como o método `print()` pertence à instância (objeto) e não à classe, você deve capturar a instância primeiro.

Ambas as abordagens abaixo realizam exatamente a mesma operação e referenciam o **mesmo endereço de memória**:

```java
package br.com.criacionais.singleton;

public class Main {
    public static void main(String[] args) {
        
        // Abordagem 1: Acesso direto através da constante pública do Enum
        Printer.INSTANCE.print();
        
        // Abordagem 2: Acesso através do método estático tradicional do padrão
        Printer.getInstance().print();
        
        // Prova de que a instância é única na memória (Retorna 'true')
        Printer p1 = Printer.INSTANCE;
        Printer p2 = Printer.getInstance();
        System.out.println("É a mesma instância? " + (p1 == p2)); 
    }
}
```

---

## ⚖️ Análise de Vantagens e Desvantagens

### Vantagens:
* **Economia de Recursos:** Evita o desperdício de memória e processamento recriando repetidamente objetos pesados.
* **Segurança Avançada:** A JVM cuida de todo o sincronismo e proteção do ciclo de vida do objeto de forma nativa.
* **Simplicidade de Código:** Elimina a necessidade de blocos complexos de checagem dupla de sincronização (*Double-Checked Locking*).

### Desvantagens:
* **Acomodação de Estado Global:** Se mal utilizado, pode mascarar dependências ocultas no código, dificultando o rastreamento de bugs que alteram o estado do objeto global.
* **Dificuldade em Testes Unitários:** Testar classes que dependem de Singletons globais pode ser difícil em isolamento, pois o estado do Singleton pode persistir entre um teste e outro se não for reiniciado.

