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

    public List<Vector3> getAllProjectedVertices(float focalLength) {
        List<Vector3> allVertices = new ArrayList<>();
        List<int[]> allEdges = new ArrayList<>();
        int vertexOffset = 0;

        for (Shape shape : shapes) {
            // Project the current shape
            List<Vector3> projectedVertices = shape.perspectiveProject(focalLength);

            // Add the projected vertices to the list
            allVertices.addAll(projectedVertices);

            // Add the edges with adjusted indices
            for (int[] edge : shape.getEdges()) {
                allEdges.add(new int[]{edge[0] + vertexOffset, edge[1] + vertexOffset});
            }

            // Update the offset for the next shape
            vertexOffset += projectedVertices.size();
        }
        return allVertices;
    }

    public List<int[]> getAllEdges() {
        List<int[]> allEdges = new ArrayList<>();
        int vertexOffset = 0;

        for (Shape shape : shapes) {
            // Add edges with adjusted indices
            for (int[] edge : shape.getEdges()) {
                allEdges.add(new int[]{edge[0] + vertexOffset, edge[1] + vertexOffset});
            }

            vertexOffset += shape.getVertices().size();
        }
        return allEdges;
    }
}