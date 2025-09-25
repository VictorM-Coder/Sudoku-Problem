# 🧩 Resolução de Sudoku com Backtracking e SaturBFS

Este repositório contém a implementação de dois algoritmos para resolução do quebra-cabeça **Sudoku**, desenvolvidos como trabalho final da disciplina de **Projeto e Análise de Algoritmos (PAA)** do curso de Engenharia de Software da UFC – Campus Quixadá.

Os dados de execução e análises detalhadas estão disponíveis no arquivo [`Trabalho_PAA.pdf`](https://github.com/VictorM-Coder/Sudoku-Problem/blob/master/Trabalho_PAA.pdf) anexado. Este projeto é destinado para fins acadêmicos.

## 📋 Descrição

O projeto aborda o problema clássico do Sudoku, um jogo de lógica combinatória, e implementa duas estratégias de resolução:

- **Backtracking (força bruta)**
- **SaturBFS (Saturated Breadth-First Search)**

Inclui:
- Explicação detalhada dos algoritmos
- Análise de complexidade
- Prova de corretude
- Resultados de tempo de execução para diferentes tamanhos e níveis de dificuldade

## 🧠 Algoritmos Implementados

### 1. Backtracking
- Explora todas as combinações possíveis de forma recursiva
- Verifica a validade de cada inserção
- Retorna `true` se encontrar uma solução válida, `false` caso contrário

### 2. SaturBFS
- Baseado em coloração de grafos e busca em largura
- Prioriza vértices (células) com maior saturação
- Mais eficiente em instâncias complexas, mas com taxa de acerto variável

## 📊 Resultados

Foram testados sudokus de tamanhos:
- 4×4, 6×6, 8×8, 9×9, 10×10, 12×12

Com três níveis de dificuldade:
- **Fácil**, **Médio**, **Difícil**

### Tabelas de desempenho:
- **Backtracking**: 100% de precisão em todos os casos
- **SaturBFS**: Alta velocidade, mas precisão decai conforme a complexidade aumenta

Consulte as tabelas completas no relatório ([`Trabalho_PAA.pdf`](https://github.com/VictorM-Coder/Sudoku-Problem/blob/master/Trabalho_PAA.pdf)).

## 👥 Autores

- Antonio Herik Cosmo Martins  
- Bruno Lima Ferreira  
- Daniel Almeida de Freitas  
- Victor Martins Vieira


