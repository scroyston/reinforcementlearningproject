
public class OctopusNodeState {
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    
    public OctopusNodeState(double x, double y, double xVelocity, double yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getXVelocity() {
        return xVelocity;
    }
    public double getYVelocity() {
        return yVelocity;
    }
}
