import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Image implements Iterable<Image.Pixel> {
    private final BufferedImage img;
    private final int type;
    private final int width;
    private final int height;
    private final BiFunction<Integer,Integer,Integer> xyIndex;
    private final int[] rawPixels;
    private final HashSet<Pixel> pixels;

    // Reading and writing images

    Image (String filename) throws IOException {
        img = ImageIO.read(new File(filename));
        type = img.getType();
        width = img.getWidth();
        height = img.getHeight();
        xyIndex = (x,y) -> x + y * width;
        rawPixels = new int[width * height];
        img.getRGB(0, 0, width, height, rawPixels, 0, width);
        pixels = new HashSet<>();
        for (Pixel px : this) {
            pixels.add(px);
        }
    }

    Image (int type, int width, int height, HashSet<Pixel> pixels) {
        img = new BufferedImage(width, height, type);
        this.type = type;
        this.width = width;
        this.height = height;
        xyIndex = (x,y) -> x + y * width;
        this.pixels = pixels;
        this.rawPixels = new int[width * height];
        for (Pixel px : pixels) rawPixels[xyIndex.apply(px.x,px.y)] = px.color;
        img.setRGB(0, 0, width, height, rawPixels, 0, width);
    }

    void write (String filename) throws IOException {
        int dot = filename.lastIndexOf('.');
        String fmt = filename.substring(dot+1);
        ImageIO.write(img, fmt, new File(filename));
    }

    // For debugging

    int getType () { return type; }
    int getWidth () { return width; }
    int getHeight () { return height; }
    BiFunction<Integer,Integer,Integer> getXyIndex () { return xyIndex; }
    int[] getRawPixels () { return rawPixels; }
    HashSet<Pixel> getPixels () { return pixels; }

    // Accessing pixels and their neighbors

    Pixel mkPixel (int x, int y) {
        return new Pixel(x, y, rawPixels[xyIndex.apply(x,y)]);
    }

    Set<Pixel> topRow () {
        HashSet<Pixel> result = new HashSet<>();
        for (int x=0; x<width; x++) result.add(mkPixel(x,0));
        return result;
    }

    /**
     * Return the neighbors north, east, south, and west of
     * the given pixel. Pixels on the boundary will have
     * fewer than 4 neighbors.
     */
    Set<Pixel> getHVneighbors(Pixel px) {
        Set<Pixel> neighbors = new HashSet<>();
        if (px.north().isPresent()) {
            neighbors.add(px.north().get());
        }
        if (px.east().isPresent()) {
            neighbors.add(px.east().get());
        }
        if (px.south().isPresent()) {
            neighbors.add(px.south().get());
        }
        if (px.west().isPresent()) {
            neighbors.add(px.west().get());
        }
        return neighbors;
    }

    /**
     * Return the neighbors southwest, south, and southeast
     * of the given pixel. Pixels on the boundary will have
     * fewer than 3 neighbors
     */
    Set<Pixel> getBelowNeighbors(Pixel px) {
        Set<Pixel> neighbors = new HashSet<> ();
        if (px.southWest().isPresent()) {
            neighbors.add(px.southWest().get());
        }
        if (px.south().isPresent()) {
            neighbors.add(px.south().get());
        }
        if (px.southEast().isPresent()) {
            neighbors.add(px.southEast().get());
        }
        return neighbors;
    }

    // Computing energy at given pixel

    int computeEnergy (Pixel px) {
        Function<Integer, Integer> sq = n -> n * n;

        int energy = 0;
        Color c = px.getColor();
        for (Pixel npx : getHVneighbors(px)) {
            Color nc = npx.getColor();
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    /**
     * Return the pixels in row order: all the pixels
     * in the first row from left to right, then the
     * pixels in the next row from left to right, etc.
     */
    public Iterator<Pixel> iterator() {
        Iterator<Pixel> iter = new Iterator<>() {

            private int x = -1;
            private int y = 0;

            @Override
            public boolean hasNext() {
                if (x + 1 > width - 1) {
                    if (y + 1 > height - 1) {
                        return false;
                    }
                    return true;
                }
                return true;
            }

            @Override
            public Pixel next() {
                x += 1;
                if (x > width - 1) {
                    x = 0;
                    y += 1;
                }
                return mkPixel(x,y);
            }
        };

        return iter;
    }

    /**
     * The method takes a path containing the pixels to
     * remove from the current. It constructs a new
     * image with these pixels removed.
     *
     * Using the method Path.xPositions will simplify
     * the implementation considerably.
     */
    Image cutSeam (Path<Image.Pixel> path) {
        HashSet<Pixel> newPixels = new HashSet<>();
        int[] temp = path.xPositions(path);
        for (Pixel px : this){
            if (temp[px.y] != px.x) {
                if (temp[px.y] < px.x) {
                    Pixel add = new Pixel(px.x - 1, px.y, px.color);
                    newPixels.add(add);
                } else {
                    newPixels.add(px);
                }
            }
        }
        //System.out.println(newPixels.size());
        return new Image(this.type, this.width - 1, this.height, newPixels);
    }

    // -------------------------------------------------------

    /**
     * The class Pixel is declared as an inner class of Image,
     * so that it can refer to the width and height of the
     * image.
     */

    public class Pixel {
        public final int x, y;
        public final int color;

        Pixel (int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        Color getColor () {
            return new Color(color, true);
        }

        /**
         * Return true is the current pixel is in the
         * last row of the image.
         */
        boolean inLastRow () {
            if (this.y == height - 1) {return true;}
            return false;
        }

        /**
         * The next few methods return the pixel in
         * the given direction if it exists.
         */

        Optional<Pixel> north () {
            if (this.y - 1 > -1){
                return Optional.of(mkPixel(this.x, this.y - 1));
            }
            return Optional.empty();
        }

        Optional<Pixel> east () {
            if (this.x + 1 <= width - 1){
                return Optional.of(mkPixel(this.x + 1, this.y));
            }
            return Optional.empty();
        }

        Optional<Pixel> south () {
            if (this.y + 1 <= height - 1){
                return Optional.of(mkPixel(this.x, this.y + 1));
            }
            return Optional.empty();
        }

        Optional<Pixel> west () {
            if (this.x - 1 > -1){
                return Optional.of(mkPixel(this.x - 1, this.y));
            }
            return Optional.empty();
        }

        Optional<Pixel> southEast () {
            if (this.y + 1 <= height - 1 && this.x + 1 <= width - 1){
                return Optional.of(mkPixel(this.x + 1, this.y + 1));
            }
            return Optional.empty();
        }

        Optional<Pixel> southWest () {
            if (this.y + 1 <= height - 1 && this.x - 1 > -1){
                return Optional.of(mkPixel(this.x - 1, this.y + 1));
            }
            return Optional.empty();
        }

        Pixel moveWest () {
            return new Pixel(x-1,y,color);
        }

        public boolean equals (Object other) {
            if (other instanceof Pixel px)
                return x == px.x && y == px.y && color == px.color;
            return false;
        }

        public int hashCode () {
            return x + 17*y + 31*color;
        }

        public String toString () {
            return String.format("%d @ [%d,%d]", color, x, y);
        }
    }
}
