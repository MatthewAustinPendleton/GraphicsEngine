import java.util.ArrayList;
import java.util.List;

public class Shape {

    private List<Vector3> vertices; // List storing 3D points
    private List<int[]> edges;      // List to store edges as pairs of vertices

    public Shape() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vector3 vertex) {
        vertices.add(vertex);
    }

    public void addEdge(int startVertex, int endVertex) {
        edges.add(new int[]{startVertex, endVertex});
    }

    public List<int[]> getEdges() {
        return edges;
    }

    public List<Vector3> getVertices() {
        return vertices;
    }

    public void translate(float tx, float ty, float tz) {
        for (Vector3 vertex : vertices) {
            vertex.setX(vertex.getX() + tx);
            vertex.setY(vertex.getY() + ty);
            vertex.setZ(vertex.getZ() + tz);
        }
    }
}
