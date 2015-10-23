package coursera.algs_1.percolation;

import coursera.api.WeightedQuickUnionUF;

public class Percolation {

    private static final boolean OPEN = true;
    private static final int MIN_AVAILABLE_SIZE = 1;
    private static final int OFFSET = 2;
    private boolean[] map;
    private final int gridSize;
    private final int graphMapSize;
    private WeightedQuickUnionUF unionUF;

    public Percolation(int gridSize) {
        if (gridSize <= 0) throw new IllegalArgumentException("Grid size should be > 0");
        this.gridSize = gridSize;
        this.graphMapSize = gridSize * gridSize + OFFSET;
        map = new boolean[graphMapSize];
        unionUF = new WeightedQuickUnionUF(graphMapSize);
        for (int i = 1, g = graphMapSize - 1; i <= gridSize; i++) {
            unionUF.union(0, i);
            unionUF.union(g, g - i);
        }
    }

    public void open(int i, int j) {
        final int mapIndex = mapIndex(i, j);
        map[mapIndex] = OPEN;
        int dj = j + 1, di = i + 1;
        if (dj <= gridSize) connect(i, dj, mapIndex);
        if (di <= gridSize) connect(di, j, mapIndex);
        dj = j - 1;
        di = i - 1;
        if (dj > 0) connect(i, dj, mapIndex);
        if (di > 0) connect(di, j, mapIndex);
    }

    public boolean isOpen(int i, int j) {
        return map[mapIndex(i, j)];
    }

    public boolean isFull(int i, int j) {
        return isOpen(i, j) && unionUF.connected(0, mapIndex(i, j));
    }

    public boolean percolates() {
        return unionUF.connected(0, graphMapSize - 1);
    }

    private int mapIndex(int i, int j) {
        return gridSize * offset(i) + offset(j) + 1;
    }

    private int offset(int pos) {
        return --pos;
    }

    private void connect(int i, int j, int prevMapIndex) {
        final int mapIndex = mapIndex(i, j);
        if (map[mapIndex]) unionUF.union(mapIndex, prevMapIndex);
    }
}
