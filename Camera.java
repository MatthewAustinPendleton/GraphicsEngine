public class Camera {

    private Vector3 position; // Camera position in 3D space
    private float pitch;      // Up and down rotation
    private float yaw;        // Left and right rotation
    private float speed;      // Movement speed

    public Camera(Vector3 startPosition, float speed) {
        this.position = startPosition;
        this.pitch = 0;
        this.yaw = 0;
        this.speed = speed;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    // Methods to control camera movement
    public void moveForward(float delta) {
        position.setX(position.getX() + speed * delta * (float) Math.sin(Math.toRadians(yaw)));
        position.setZ(position.getZ() - speed * delta * (float) Math.cos(Math.toRadians(yaw)));
    }

    public void moveBackward(float delta) {
        position.setX(position.getX() - speed * delta * (float) Math.sin(Math.toRadians(yaw)));
        position.setZ(position.getZ() + speed * delta * (float) Math.cos(Math.toRadians(yaw)));
    }

    public void strafeLeft(float delta) {
        position.setX(position.getX() - speed * delta * (float) Math.cos(Math.toRadians(yaw)));
        position.setZ(position.getZ() - speed * delta * (float) Math.sin(Math.toRadians(yaw)));
    }

    public void strafeRight(float delta) {
        position.setX(position.getX() + speed * delta * (float) Math.cos(Math.toRadians(yaw)));
        position.setZ(position.getZ() + speed * delta * (float) Math.sin(Math.toRadians(yaw)));
    }

    public void moveUp(float delta) {
        position.setY(position.getY() + speed * delta);
    }

    public void moveDown(float delta) {
        position.setY(position.getY() - speed * delta);
    }

    public void rotate(float pitchDelta, float yawDelta) {
        pitch += pitchDelta;
        yaw += yawDelta;
    }
}