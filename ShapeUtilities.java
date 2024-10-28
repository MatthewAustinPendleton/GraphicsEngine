import java.util.ArrayList;
import java.util.List;

public class ShapeUtilities {
    public static void combineShapes(List<Vector3> allVertices, List<int[]> allEdges,
                                     List<Vector3> projectedVertices, List<int[]> edges,
                                     int offset) {
        allVertices.addAll(projectedVertices);

        for (int[] edge : edges) {
            allEdges.add(new int[]{edge[0] + offset, edge[1] + offset});
        }
    }
}