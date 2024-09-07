package org.ufc.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaturBFS {
    private final Integer[] vertexColor;
    private final Integer[][] graphAdjacencyMatrix;
    private final Integer numberOfVertices;
    private final Integer degreeSudoku;

    public SaturBFS(
            Integer[] vertexColor,
            Integer[][] graphAdjacencyMatrix,
            Integer numberOfVertices,
            Integer degreeSudoku
    ) {
        this.vertexColor = vertexColor;
        this.graphAdjacencyMatrix = graphAdjacencyMatrix;
        this.numberOfVertices = numberOfVertices;
        this.degreeSudoku = degreeSudoku;
    }

    public boolean saturBFS() {
        Integer verticeInicio = highestSaturationVertex();
        Integer cor = colorirVertice(verticeInicio, degreeSudoku);
        vertexColor[verticeInicio] = cor;

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
                vertexColor[verticeAdj] = cor;
                fila.add(verticeAdj);

                verticeAdj =
                        highestSaturationAdjacent(findAdjacentyVertex(vertice));
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
}
