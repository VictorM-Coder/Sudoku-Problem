package org.ufc.sudoku.algorithms;

import org.ufc.sudoku.SudokuType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaturBFS implements SudokuSolver {
    private Integer[] vertexColor;
    private final Integer[][] graphAdjacencyMatrix;
    private final Integer numberOfVertices;
    private final Integer degreeSudoku;

    public SaturBFS(SudokuType sudokuType
    ) {
        this.graphAdjacencyMatrix = generateAdjacencyMatrix(sudokuType);
        this.numberOfVertices = sudokuType.getDegree()*sudokuType.getDegree();
        this.degreeSudoku = sudokuType.getDegree();
        this.vertexColor = new Integer[this.numberOfVertices];
    }

    public boolean solve(Integer[] sudoku) {
        this.vertexColor = sudoku;
        Integer verticeInicio = highestSaturationVertex();
        Integer cor = colorirVertice(verticeInicio, degreeSudoku);
        sudoku[verticeInicio] = cor;

        List<Integer> fila = new ArrayList<>();
        fila.add(verticeInicio);

        while (!fila.isEmpty()) {
            Integer vertice = fila.getFirst();
            fila.removeFirst();

            Integer verticeAdj =
                    highestSaturationAdjacent(findAdjacentyVertex(vertice));

            while (verticeAdj != null) {
                cor = colorirVertice(verticeAdj, degreeSudoku);
                if (cor == null) {
                    return false;
                }
                sudoku[verticeAdj] = cor;
                fila.add(verticeAdj);

                verticeAdj = highestSaturationAdjacent(findAdjacentyVertex(vertice));
            }
        }
        return true;
    }

    private Integer highestSaturationAdjacent(List<Integer> vertices) {
        Integer[] quantAdjacentesColoridos = new Integer[numberOfVertices];
        //Selecionar o vertice com maior saturação
        Integer indexVertice = -1;
        Integer saturacaoVertice = 0;

        for (Integer v : vertices) {
            if (vertexColor[v] == null) {
                Integer grauSaturacao = grauSaturacaoVertice(v);
                quantAdjacentesColoridos[v] = grauSaturacao;
                indexVertice = 0;
            }
        }

        //Saber qual a menor saturação
        for (int i = 0; i < numberOfVertices; i++) {
            if (quantAdjacentesColoridos[i] != null) {
                if (saturacaoVertice < quantAdjacentesColoridos[i]) {
                    indexVertice = i;
                    saturacaoVertice = quantAdjacentesColoridos[i];
                }
            }
        }

        //Buscar os vértices com mesma saturação
        List<Integer> verticesMesmaSaturacao = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            if (quantAdjacentesColoridos[i] != null) {
                if (saturacaoVertice == quantAdjacentesColoridos[i]) {
                    verticesMesmaSaturacao.add(i);
                }
            }
        }

        if (!verticesMesmaSaturacao.isEmpty()) {
            //Sortear
            indexVertice = verticesMesmaSaturacao.get(
                    new Random().nextInt(verticesMesmaSaturacao.size())
            );
        }

        if (indexVertice == -1) {
            return null;
        }

        return indexVertice;
    }

    private Integer colorirVertice(Integer vertice, Integer totalCores) {
        List<Integer> cores = new ArrayList<>();

        for (int cor = 1; cor <= totalCores; cor++) {
            if (podeColorir(vertice, cor)) {
                cores.add(cor);
            }
        }

        if (cores.isEmpty()) {
            return null;
        }

        //Escolho uma cor aleatória do conjunto de cores que podem ser utilizadas e retorno;
        int posicaoCor = new Random().nextInt(cores.size());
        return cores.get(posicaoCor);
    }

    private boolean podeColorir(Integer vertice, Integer cor) {
        List<Integer> adjacentes = findAdjacentyVertex(vertice);
        for (Integer adjacente : adjacentes) {
            if (vertexColor[adjacente] == cor) {
                return false;
            }
        }
        return true;
    }

    private Integer highestSaturationVertex() {
        Integer[] quantAdjacentesColoridos = new Integer[numberOfVertices];

        for (int v = 0; v < numberOfVertices; v++) {
            if (vertexColor[v] == null) {
                Integer grauSaturacao = grauSaturacaoVertice(v);
                quantAdjacentesColoridos[v] = grauSaturacao;
            }
        }

        //Selecionar o vertice com maior saturação
        Integer indexVertice = -1;
        Integer saturacaoVertice = 0;

        for (int i = 0; i < numberOfVertices; i++) {
            if (quantAdjacentesColoridos[i] != null) {
                if (saturacaoVertice < quantAdjacentesColoridos[i]) {
                    indexVertice = i;
                    saturacaoVertice = quantAdjacentesColoridos[i];
                }
            }
        }

        //Buscar os vértices com mesma saturação
        List<Integer> verticesMesmaSaturacao = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            if (quantAdjacentesColoridos[i] != null) {
                if (saturacaoVertice == quantAdjacentesColoridos[i]) {
                    verticesMesmaSaturacao.add(i);
                }
            }
        }

        if (!verticesMesmaSaturacao.isEmpty()) {
            //Sortear
            indexVertice = verticesMesmaSaturacao.get(
                    new Random().nextInt(verticesMesmaSaturacao.size()));
        }

        if (indexVertice == -1) {
            return null;
        }

        return indexVertice;
    }

    private Integer grauSaturacaoVertice(int vertice) {
        Integer contCores = 0;
        List<Integer> adjacentes = findAdjacentyVertex(vertice);
        for (Integer adjacente : adjacentes) {
            if (vertexColor[adjacente] != null) {
                contCores++;
            }
        }
        return contCores;
    }

    private List<Integer> findAdjacentyVertex(int vertice) {
        List<Integer> adjacentVertex = new ArrayList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            boolean isAdjacente = graphAdjacencyMatrix[vertice][i] == 1;
            if (isAdjacente) {
                adjacentVertex.add(i);
            }
        }
        return adjacentVertex;
    }

    public static Integer[][] generateAdjacencyMatrix(SudokuType sudokuType) {
        int sudokuDegree = sudokuType.getDegree();
        int numbersVertex = sudokuDegree * sudokuDegree;
        int contVertex = 0;

        Integer[][] vertexLabelMatrix = new Integer[sudokuDegree][sudokuDegree];
        Integer[][] adjacencyMatrix = new Integer[numbersVertex][numbersVertex];

        for(int i = 0; i < sudokuDegree; i++) {
            for(int j = 0; j < sudokuDegree; j++) {
                vertexLabelMatrix[i][j] = contVertex;
                contVertex++;
            }
        }

        for(int i = 0; i < numbersVertex; i++) {
            for (int j = 0; j < numbersVertex; j++) {
                if(j >= (i/ sudokuDegree)* sudokuDegree && j < (i/ sudokuDegree)* sudokuDegree + sudokuDegree)
                    adjacencyMatrix[i][j] = 1;
                else if(j% sudokuDegree == i% sudokuDegree)
                    adjacencyMatrix[i][j] = 1;
                else
                    adjacencyMatrix[i][j] = 0;
            }

        }

        List<Integer> verticesAdjacentes = new ArrayList<>();

        int inicioColuna = 0;
        for(int i = sudokuType.getBlockLineNumbers() ; i <= sudokuDegree; i += sudokuType.getBlockLineNumbers()) {
            int inicioLinha = 0;
            for(int f = sudokuType.getBlockLineNumbers() ; f <= sudokuDegree; f += sudokuType.getBlockLineNumbers()) {

                for (int m = inicioLinha; m < f; m++) {
                    for (int k = inicioColuna; k < i; k++) {
                        Integer vertice = vertexLabelMatrix[m][k];
                        verticesAdjacentes.add(vertice);
                    }
                }

                //Ligar os vértices
                for(int l = 0; l < verticesAdjacentes.size(); l++) {
                    int v1 = verticesAdjacentes.get(l);
                    for(int j = l; j < verticesAdjacentes.size(); j++) {
                        int v2 = verticesAdjacentes.get(j);
                        adjacencyMatrix[v1][v2] = adjacencyMatrix[v2][v1] = 1;
                    }
                }
                verticesAdjacentes.clear();

                inicioLinha = f;
            }
            inicioColuna = i;
        }

        for(int i = 0; i < numbersVertex; i++) {
            adjacencyMatrix[i][i] = 0;
        }

        return adjacencyMatrix;
    }
}
