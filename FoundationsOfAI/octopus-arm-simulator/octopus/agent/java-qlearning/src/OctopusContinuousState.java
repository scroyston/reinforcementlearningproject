import rlframework.State;

public class OctopusContinuousState implements State {
    private double[] state;
    private int numNodes;
    
    public OctopusContinuousState(double[] state) {
        this.state = state;
        numNodes = (state.length - 2) / 4;
    }
    
    public double[] getState() {
        return state;
    }
    
    public double getBaseAngle() {
        return state[0];
    }
    
    public double getBaseAngularVelocity() {
        return state[1];
    }
    
    public int getNumberOfNodes() {
        return numNodes;
    }
    
    /**
     * Returns the state variables for a particular node, the nodes are indexed
     * such that all dorsal nodes from base to tip are first, then follow the 
     * ventral nodes from base to tip.
     * @param nodeIndex 0-based index of the node whose state you want to recover.
     * @return
     */
    public OctopusNodeState getNodeState(int nodeIndex) {
        return new OctopusNodeState(state[2+4*nodeIndex],state[3+4*nodeIndex],state[4+4*nodeIndex],state[5+4*nodeIndex]);
    }
}
