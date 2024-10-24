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

    public void rotateAlongX(float rotationAngleInDegrees) {
        // Convert degrees to radians
        float angleInRadians = (float) Math.toRadians(rotationAngleInDegrees);
        float cosTheta = (float) Math.cos(angleInRadians);
        float sinTheta = (float) Math.sin(angleInRadians);

        for (Vector3 vertex : vertices) {
            float y = vertex.getY();
            float z = vertex.getZ();

            // Apply the rotation about the x-axis
            float newY = y * cosTheta - z * sinTheta;
            float newZ = y * sinTheta + z * cosTheta;

            vertex.setY(newY);
            vertex.setZ(newZ);
        }
    }

    public void rotateAlongY(float rotationAngleInDegrees) {
        float angleInRadians = (float) Math.toRadians(rotationAngleInDegrees);
        float cosTheta = (float) Math.cos(angleInRadians);
        float sinTheta = (float) Math.sin(angleInRadians);

        for (Vector3 vertex : vertices) {
            float x = vertex.getX();
            float z = vertex.getZ();

            // Apply the rotation about the y-axis
            float newX = x * cosTheta + z * sinTheta;
            float newZ = -x * sinTheta + z * cosTheta;

            vertex.setX(newX);
            vertex.setZ(newZ);
        }
    }

    public void printShape() {
        for (Vector3 vertex : vertices) {
            vertex.print();
        }
    }
}