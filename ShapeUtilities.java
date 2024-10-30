import java.util.ArrayList;
import java.util.List;

public class ShapeUtilities {
    public static List<Vector3> perspectiveProject(List<Vector3> vertices, float focalLength) {
        List<Vector3> projectedVertices = new ArrayList<>();
        for (Vector3 vertex : vertices) {
            float x = vertex.getX();
            float y = vertex.getY();
            float z = vertex.getZ();

            // Apply perspective projection
            float xPrime = (focalLength * x) / (z + focalLength);
            float yPrime = (focalLength * y) / (z + focalLength);

            projectedVertices.add(new Vector3(xPrime, yPrime, 0));
        }
        return projectedVertices;
    }
}