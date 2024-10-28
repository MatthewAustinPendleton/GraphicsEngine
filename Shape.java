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

    public void scale(float scalar) {
        for (Vector3 vertex : vertices) {
            vertex.setX(vertex.getX() * scalar);
            vertex.setY(vertex.getY() * scalar);
            vertex.setZ(vertex.getZ() * scalar);
        }
    }

    public void scale(float scalarX, float scalarY, float scalarZ) {
        for (Vector3 vertex : vertices) {
            vertex.setX(vertex.getX() * scalarX);
            vertex.setY(vertex.getY() * scalarY);
            vertex.setZ(vertex.getZ() * scalarZ);
        }
    }

    public void scaleX(float scalar) {
        for (Vector3 vertex : vertices) {
            vertex.setX(vertex.getX() * scalar);
        }
    }

    public void scaleY(float scalar) {
        for (Vector3 vertex : vertices) {
            vertex.setY(vertex.getY() * scalar);
        }
    }

    public void scaleZ(float scalar) {
        for (Vector3 vertex : vertices) {
            vertex.setZ(vertex.getZ() * scalar);
        }
    }

    public void rotateAlongX(float rotationAngleInDegrees) {
        float angleInRadians = (float) Math.toRadians(rotationAngleInDegrees);
        float cosTheta = (float) Math.cos(angleInRadians);
        float sinTheta = (float) Math.sin(angleInRadians);

        for (Vector3 vertex : vertices) {
            float y = vertex.getY();
            float z = vertex.getZ();

            // Apply the rotation matrix for the x-axis
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

            // Apply the rotation matrix for the y-axis
            float newX = x * cosTheta + z * sinTheta;
            float newZ = -x * sinTheta + z * cosTheta;

            vertex.setX(newX);
            vertex.setZ(newZ);
        }
    }

    public void rotateAlongZ(float rotationAngleInDegrees) {
        float angleInRadians = (float) Math.toRadians(rotationAngleInDegrees);
        float cosTheta = (float) Math.cos(angleInRadians);
        float sinTheta = (float) Math.sin(angleInRadians);

        for (Vector3 vertex : vertices) {
            float x = vertex.getX();
            float y = vertex.getY();

            // Apply the rotation matrix for the z-axis
            float newX = x * cosTheta - y * sinTheta;
            float newY = x * sinTheta + y * cosTheta;

            vertex.setX(newX);
            vertex.setY(newY);
        }
    }

    public void printShape() {
        for (Vector3 vertex : vertices) {
            vertex.print();
        }
    }

    public List<Vector3> perspectiveProject(float focalLength) {
        // Initialize a list for projected vertices
        List<Vector3> projectedVertices = new ArrayList<>();
        for (Vector3 vertex : vertices) {

            float x = vertex.getX();
            float y = vertex.getY();
            float z = vertex.getZ();

            // Perspective projection
            float xPrime = (focalLength * x) / (z + focalLength);
            float yPrime = (focalLength * y) / (z + focalLength);

            projectedVertices.add(new Vector3(xPrime, yPrime, 0));
        }
        return projectedVertices;
    }

    public void createCube(float size) {
        // Clear existing vertices/edges
        vertices.clear();
        edges.clear();

        // Centering cube at origin
        float halfSize = size / 2;

        addVertex(new Vector3(halfSize, halfSize, halfSize)); // Vertex 0 (A)
        addVertex(new Vector3(-halfSize, halfSize, halfSize)); // Vertex 1 (B)
        addVertex(new Vector3(-halfSize, -halfSize, halfSize)); // Vertex 2 (C)
        addVertex(new Vector3(halfSize, -halfSize, halfSize)); // Vertex 3 (D)
        addVertex(new Vector3(halfSize, halfSize, -halfSize)); // Vertex 4 (E)
        addVertex(new Vector3(-halfSize, halfSize, -halfSize)); // Vertex 5 (F)
        addVertex(new Vector3(-halfSize, -halfSize, -halfSize)); // Vertex 6 (G)
        addVertex(new Vector3(halfSize, -halfSize, -halfSize)); // Vertex 7 (H)

        // 12 edges of cube
        addEdge(0, 1); // AB
        addEdge(1, 2); // BC
        addEdge(2, 3); // CD
        addEdge(3, 0); // DA
        addEdge(4, 5); // EF
        addEdge(5, 6); // FG
        addEdge(6, 7); // GH
        addEdge(7, 4); // HE
        addEdge(0, 4); // AE
        addEdge(1, 5); // BF
        addEdge(2, 6); // CG
        addEdge(3, 7); // DH
    }
}