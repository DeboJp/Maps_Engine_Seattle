package seamfinding;

import seamfinding.energy.EnergyFunction;

import java.util.*;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {


        double[][] table = new double[picture.width()][picture.height()];
        for (int i=0; i < picture.height(); i++)
        {
            table[0][i] = f.apply(picture, 0, i);
        }

        for (int x=1; x < picture.width(); x++)
        {
            for (int y=0; y < picture.height(); y++)
            {

                double m = table[x-1][y];
                double up = Double.POSITIVE_INFINITY;
                double down = Double.POSITIVE_INFINITY;
                if (y!=0) {up = table[x-1][y-1];}
                if (y!=picture.height() - 1) { down = table[x-1][y+1];}

                double min = m;
                if (up < min) {min = up;}
                if (down < min) {min = down;}

                table[x][y] = f.apply(picture, x, y) + min;
            }
        }

        /*
        for (int i=0; i < picture.width(); i++)
        {
            for (int j=0; j < picture.height(); j++)
            {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
        */

        List<Integer> result = new ArrayList<>();
        int mini = 0;
        for (int i=1; i < picture.height(); i++)
        {
            if (table[picture.width() - 1][i] < table[picture.width() - 1][mini])
            {
                mini = i;

            }
        }


        result.add(mini);
        int y = mini;
        for (int x = picture.width()-1; x >=1; x--)
        {
            /*
            TreeMap<Double, Integer> map = new TreeMap<>();
            map.put(table[x-1][y], y);
            if (y!=0) {map.put(table[x-1][y-1], y-1);}
            if (y!=picture.height() - 1) {map.put(table[x-1][y+1], y+1);}
            int min = map.firstEntry().getValue();

             */

            double m = table[x-1][y];
            double up = Double.POSITIVE_INFINITY;
            double down = Double.POSITIVE_INFINITY;
            if (y!=0) {up = table[x-1][y-1];}
            if (y!=picture.height() - 1) { down = table[x-1][y+1];}

            double min = m;
            int minIndex = y;
            if (up < min)
            {
                min = up;
                minIndex = y -1;
            }
            if (down < min)
            {
                min = down;
                minIndex = y + 1;
            }

            result.add(minIndex);
            y = minIndex;


        }

        Collections.reverse(result);

        return result;


    }
}
