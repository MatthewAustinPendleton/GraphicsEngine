import java.awt.event.*;

public class CameraController implements KeyListener, MouseMotionListener {

    private Camera camera;
    private float mouseSensitivity = 0.1f;
    private boolean[] keys = new boolean[256];

    // Track the last mouse position
    private int lastMouseX = -1;
    private int lastMouseY = -1;

    public CameraController(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < keys.length) {
            keys[keyCode] = true;
            System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < keys.length) {
            keys[keyCode] = false;
            System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Only update if there is a valid previous mouse position
        if (lastMouseX != -1 && lastMouseY != -1) {
            int dx = x - lastMouseX;
            int dy = y - lastMouseY;

            // Update the camera rotation based on mouse movements
            camera.rotate(-dy * mouseSensitivity, dx * mouseSensitivity);
            System.out.println("Mouse dragged: dx = " + dx + ", dy = " + dy);
        }

        // Update last mouse position
        lastMouseX = x;
        lastMouseY = y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Update last mouse position
        lastMouseX = e.getX();
        lastMouseY = e.getY();
    }

    // Update camera position based on key states
    public void update(float deltaTime) {
        if (keys[KeyEvent.VK_W]) camera.moveForward(deltaTime);
        if (keys[KeyEvent.VK_S]) camera.moveBackward(deltaTime);
        if (keys[KeyEvent.VK_A]) camera.strafeLeft(deltaTime);
        if (keys[KeyEvent.VK_D]) camera.strafeRight(deltaTime);
        if (keys[KeyEvent.VK_SPACE]) camera.moveUp(deltaTime);
        if (keys[KeyEvent.VK_C]) camera.moveDown(deltaTime);
    }
}
