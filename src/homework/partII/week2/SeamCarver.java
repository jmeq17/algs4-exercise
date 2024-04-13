package homework.partII.week2;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private int height;
    private int width;

    private double[][] energy;  // array of energy. x is col, y is row.

    // create homework.a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();

        this.picture = new Picture(picture);
        height = picture.height();
        width = picture.width();

        energy = new double[width][height];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                energy[x][y] = energy(x, y);
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException();

        if (x == 0 || x == width - 1 || y == 0 || y == height - 1) return 1000;

        int rgbUp = picture.getRGB(x, y - 1);
        int rgbDown = picture.getRGB(x, y + 1);
        int rgbLeft = picture.getRGB(x - 1, y);
        int rgbRight = picture.getRGB(x + 1, y);

        double rx = Math.pow(((rgbRight >> 16) & 0xFF) - ((rgbLeft >> 16) & 0xFF), 2);
        double gx = Math.pow(((rgbRight >> 8) & 0xFF) - ((rgbLeft >> 8) & 0xFF), 2);
        double bx = Math.pow(((rgbRight) & 0xFF) - ((rgbLeft) & 0xFF), 2);

        double ry = Math.pow(((rgbDown >> 16) & 0xFF) - ((rgbUp >> 16) & 0xFF), 2);
        double gy = Math.pow(((rgbDown >> 8) & 0xFF) - ((rgbUp >> 8) & 0xFF), 2);
        double by = Math.pow(((rgbDown) & 0xFF) - ((rgbUp) & 0xFF), 2);

        double energy = rx + gx + bx + ry + gy + by;

        return Math.sqrt(energy);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int[] horizontalSeam = new int[width];
        if (height == 1 || height == 2) return horizontalSeam;

        int[] edgeTo = new int[height * width + 2];
        double[] distTo = new double[height * width + 2];
        for (int i = 1; i <= height; i++) distTo[i] = 1000;
        for (int i = height + 1; i < distTo.length; i++) distTo[i] = Double.POSITIVE_INFINITY;

        for (int x = 0; x < width - 1; x++) {
            int v = x * height + 1;
            for (int y = 1; y < height - 1; y++) {
                v++;
                int w = v + height - 1;

                for (int i = 0; i < 3; i++) {
                    if (distTo[w] > distTo[v] + energy[x + 1][y - 1 + i]) {
                        distTo[w] = distTo[v] + energy[x + 1][y - 1 + i];
                        edgeTo[w] = v;
                    }

                    w++;
                }
            }
        }

        int w = height * width + 1;

        for (int v = height * (width - 1) + 2; v < w - 1; v++) {
            if (distTo[w] > distTo[v]) {
                distTo[w] = distTo[v];
                edgeTo[w] = v;
            }
        }

        int i = width - 1;

        while (w > height) {
            horizontalSeam[i] = edgeTo[w] % height - 1;
            w = edgeTo[w];
            i--;
        }

        return horizontalSeam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[] verticalSeam = new int[height];
        if (width == 1 || width == 2) return verticalSeam;

        int[] edgeTo = new int[height * width + 2];
        double[] distTo = new double[height * width + 2];
        for (int i = 1; i <= width; i++) distTo[i] = 1000;
        for (int i = width + 1; i < distTo.length; i++) distTo[i] = Double.POSITIVE_INFINITY;

        for (int y = 0; y < height - 1; y++) {
            int v = y * width + 1;
            for (int x = 1; x < width - 1; x++) {
                v++;
                int w = v + width - 1;

                for (int i = 0; i < 3; i++) {
                    if (distTo[w] > distTo[v] + energy[x - 1 + i][y + 1]) {
                        distTo[w] = distTo[v] + energy[x - 1 + i][y + 1];
                        edgeTo[w] = v;
                    }

                    w++;
                }
            }
        }

        int w = height * width + 1;

        for (int v = width * (height - 1) + 2; v < w - 1; v++) {
            if (distTo[w] > distTo[v]) {
                distTo[w] = distTo[v];
                edgeTo[w] = v;
            }
        }

        int i = height - 1;

        while (w > width) {
            verticalSeam[i] = edgeTo[w] % width - 1;
            w = edgeTo[w];
            i--;
        }

        return verticalSeam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (height <= 1) throw new IllegalArgumentException();
        if (seam == null) throw new IllegalArgumentException();
        if (seam.length != width) throw new IllegalArgumentException();
        if (seam[0] < 0 || seam[0] >= height) throw new IllegalArgumentException();
        for (int i = 1; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= height) throw new IllegalArgumentException();
            if (Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }

        height--;

        Picture newPicture = new Picture(width, height);
        double[][] eng = new double[width][height];

        for (int x = 0; x < width; x++) {
            int tem = 0;
            for (int y = 0; y < height; y++) {
                if (y == seam[x]) tem = 1;
                newPicture.setRGB(x, y, picture.getRGB(x, y + tem));
                eng[x][y] = energy[x][y + tem];
            }
        }

        picture = newPicture;

        for (int x = 0; x < width; x++) {
            if (seam[x] != 0) eng[x][seam[x] - 1] = energy(x, seam[x] - 1);
            if (seam[x] != height) eng[x][seam[x]] = energy(x, seam[x]);
        }

        energy = eng;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (width <= 1) throw new IllegalArgumentException();
        if (seam == null) throw new IllegalArgumentException();
        if (seam.length != height) throw new IllegalArgumentException();
        if (seam[0] < 0 || seam[0] >= width) throw new IllegalArgumentException();
        for (int i = 1; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= width) throw new IllegalArgumentException();
            if (Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }

        width--;

        Picture newPicture = new Picture(width, height);
        double[][] eng = new double[width][height];

        for (int y = 0; y < height; y++) {
            int tem = 0;
            for (int x = 0; x < width; x++) {
                if (x == seam[y]) tem = 1;
                newPicture.setRGB(x, y, picture.getRGB(x + tem, y));
                eng[x][y] = energy[x + tem][y];
            }
        }

        picture = newPicture;

        for (int y = 0; y < height; y++) {
            if (seam[y] != 0) eng[seam[y] - 1][y] = energy(seam[y] - 1, y);
            if (seam[y] != width) eng[seam[y]][y] = energy(seam[y], y);
        }

        energy = eng;
    }
}
