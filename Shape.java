import java.util.ArrayList;
import java.util.List;

public class Shape {

    public List<Vector3> vertices; // List storing 3D points
    public List<int[]> edges;      // List to store edges as pairs of vertices

    public Shape() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void rotate(float rotationX, float rotationY, float rotationZ) {
        rotateAlongX(rotationX);
        rotateAlongY(rotationY);
        rotateAlongZ(rotationZ);
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

    public void createPyramid(float baseSize, float height) {
        // Clear existing vertices/edges in shape
        vertices.clear();
        edges.clear();

        // Half the base size to center at the origin
        float halfBase = baseSize / 2;

        // Define the 5 vertices of the pyramid
        addVertex(new Vector3(halfBase, 0, halfBase)); // Vertex 0 (A)
        addVertex(new Vector3(-halfBase, 0, halfBase)); // Vertex 1 (B)
        addVertex(new Vector3(-halfBase, 0, -halfBase)); // Vertex 2 (C)
        addVertex(new Vector3(halfBase, 0, -halfBase)); // Vertex 3 (D)
        addVertex(new Vector3(0, height, 0)); // Vertex 4 (apex)

        // Define the 8 edges of the pyramid
        addEdge(0, 1); // AB
        addEdge(1, 2); // BC
        addEdge(2, 3); // CD
        addEdge(3, 0); // DA
        addEdge(0, 4); // AE
        addEdge(1, 4); // BE
        addEdge(2, 4); // CE
        addEdge(3, 4); // DE
    }

    public void createCylinder(float radius, float height, int segments) {
        // Clear existing vertices/edges
        vertices.clear();
        edges.clear();

        // Create vertices for the top and bottom circles
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float x = radius * (float) Math.cos(angle);
            float z = radius * (float) Math.sin(angle);

            // Add top and bottom vertices
            addVertex(new Vector3(x, height / 2, z)); // Top vertex
            addVertex(new Vector3(x, -height / 2, z)); // Bottom vertex
        }

        // Create edges for the top and bottom circles
        for (int i = 0; i < segments; i++) {
            int next = (i + 1) % segments; // Wrap around to connect the last to the first

            // Top circle edges
            addEdge(i * 2, next * 2);

            // Bottom circle edges
            addEdge(i * 2 + 1, next * 2 + 1);

            // Side edges between top and bottom
            addEdge(i * 2, i * 2 + 1);
        }
    }

    public void createSphere(float radius, int latitudeSegments, int longitudeSegments) {
        // Clear
        vertices.clear();
        edges.clear();

        // Generate vertices
        for (int i = 0; i <= latitudeSegments; i++) {
            double theta = Math.PI * i / latitudeSegments; // polar angle
            float y = radius * (float) Math.cos(theta); // height

            for (int j = 0; j < longitudeSegments; j++) {
                double phi = 2 * Math.PI * j / longitudeSegments; // azimuthal angle
                float x = radius * (float) (Math.sin(theta) * Math.cos(phi));
                float z = radius * (float) (Math.sin(theta) * Math.sin(phi));

                addVertex(new Vector3(x, y, z));
            }
        }

        // Generate edges
        for (int i = 0; i < latitudeSegments; i++) {
            for (int j = 0; j < longitudeSegments; j++) {
                int current = i * longitudeSegments + j;
                int next = (j + 1) % longitudeSegments + i * longitudeSegments;
                int upper = (i + 1) * longitudeSegments + j;
                addEdge(current, next);
                if (i < latitudeSegments - 1) {
                    addEdge(current, upper);
                }
            }
        }
    }
}