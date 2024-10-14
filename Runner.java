public class Runner {
    public static void main(String[] args) {
        Shape unitCube = new Shape();
        unitCube.addVertex(new Vector3(1, 1, 1)); // call it vertex A     0
        unitCube.addVertex(new Vector3(-1, 1, 1)); // vertex B            1
        unitCube.addVertex(new Vector3(-1, -1, 1)); // vertex C           2
        unitCube.addVertex(new Vector3(1, -1, 1)); // vertex D            3
        unitCube.addVertex(new Vector3(1, 1, -1)); // vertex E            4
        unitCube.addVertex(new Vector3(-1, 1, -1)); // vertex F           5
        unitCube.addVertex(new Vector3(-1, -1, -1)); // vertex G          6
        unitCube.addVertex(new Vector3(1, -1, -1)); // vertex H           7

        // Cubes have 12 edges:
        unitCube.addEdge(0 ,1);
        unitCube.addEdge(1, 3);
        unitCube.addEdge(3, 7);
        unitCube.addEdge(6, 2);
        unitCube.addEdge(0, 2);
        unitCube.addEdge(1, 5);
        unitCube.addEdge(7, 5);
        unitCube.addEdge(6, 4);
        unitCube.addEdge(0, 4);
        unitCube.addEdge(3, 2);
        unitCube.addEdge(7, 6);
        unitCube.addEdge(4, 5);

        System.out.println("Before translation: ");
        for (Vector3 vertex : unitCube.getVertices()) {
            vertex.print();
        }

        // Translate the cube by (2, 3, 1)
        unitCube.translate(2, 3, 1);

        System.out.println("After translation: ");
        for (Vector3 vertex : unitCube.getVertices()) {
            vertex.print();
        }
    }
    public static int getRandomNumber(int a, int b) {
        return (int)(Math.random() * (b - a + 1)) + a;
    }
}
