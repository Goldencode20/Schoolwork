import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SeamCarving {
    private final String filename;
    private Image img;

    SeamCarving (String filename) throws IOException {
        this.filename = filename;
        img = new Image(filename);
    }

    void write (String prefix) throws IOException {
        img.write(prefix + filename);
    }

    Image getImg () { return img; }

    /**
     * The method finds the seam with the least energy starting
     * from the current pixel and going down to the last row.
     * Here we just write a recursive solution that may
     * recompute subproblems.
     *
     * If the current pixel is at the bottom row, we return
     * a path that contains just the current pixel and its
     * energy.
     *
     * If the current cell is somewhere other than the last row,
     * then we compute the best seam from each of its southern
     * neighbors and take the minimum.
     *
     * A short elegant solution is possible using the map and
     * min methods on streams.
     */
    PathAndCost findSeamFrom (Image.Pixel px) {
        if (px.inLastRow()) {
            return new PathAndCost(Path.singleton(px), img.computeEnergy(px));
        }

        Image.Pixel smallest = img.getBelowNeighbors(px).stream().reduce((pixel1, pixel2) -> img.computeEnergy(pixel1) > (img.computeEnergy(pixel2)) ? pixel1:pixel2).get();

        PathAndCost lowerLevel = findSeamFrom(smallest);
        lowerLevel = lowerLevel.add(smallest, img.computeEnergy(smallest));
        return new PathAndCost(lowerLevel.seam(), lowerLevel.cost());
    }

    /**
     * The method finds all the seams that start at the top
     * row and returns the minimum.
     */
    Path<Image.Pixel> bestSeam () {
        PathAndCost minPath = findSeamFrom(img.mkPixel(0, 0));
        for (int i = 1; i < img.getWidth() - 1; i++) {
            PathAndCost temp = findSeamFrom(img.mkPixel(i, 0));
            if (minPath.cost() > temp.cost()) {minPath = temp;}
        }
        return minPath.seam();
    }

    void cutN (int n) {
        for (int i=0; i<n; i++) img = img.cutSeam(bestSeam());
    }
}

