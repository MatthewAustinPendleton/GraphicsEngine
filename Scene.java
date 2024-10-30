import java.util.ArrayList;
import java.util.List;

public class Scene {

    private List<Shape> shapes;

    public Scene() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public List<Vector3> getAllProjectedVertices(Camera camera, float focalLength) {
        List<Vector3> allVertices = new ArrayList<>();
        int vertexOffset = 0;

        for (Shape shape : shapes) {
            // Transform the shape based on the camera's position and orientation
            List<Vector3> transformedVertices = shape.getTransformedVertices(camera);

            // Project the transformed vertices to 2D
            List<Vector3> projectedVertices = ShapeUtilities.perspectiveProject(transformedVertices, focalLength);

            // Add projected vertices to the list
            allVertices.addAll(projectedVertices);

            vertexOffset += projectedVertices.size();
        }
        return allVertices;
    }

    public List<int[]> getAllEdges() {
        List<int[]> allEdges = new ArrayList<>();
        int vertexOffset = 0;

        for (Shape shape : shapes) {
            for (int[] edge : shape.getEdges()) {
                allEdges.add(new int[]{edge[0] + vertexOffset, edge[1] + vertexOffset});
            }
            vertexOffset += shape.getVertices().size();
        }
        return allEdges;
    }
}