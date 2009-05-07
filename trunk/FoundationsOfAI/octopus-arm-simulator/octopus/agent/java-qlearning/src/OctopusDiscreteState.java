import java.io.Serializable;

import rlframework.DiscreteState;

public class OctopusDiscreteState implements DiscreteState, Comparable, Serializable {
    private static final long serialVersionUID = 5214061107067719643L;
    private int[] discreteStates;
    private int hash;
    
    public OctopusDiscreteState(int[] discreteStates) {
        this.discreteStates = discreteStates;
        hash = 0;
        for(int i = 0; i < discreteStates.length; ++i) {
            hash *= 7;
            hash += discreteStates[i];
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OctopusDiscreteState) {
            return compareTo(obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append('[');
        for(int i = 0; i < discreteStates.length; ++i) {
            buf.append(discreteStates[i]);
            if(i < discreteStates.length - 1) buf.append(',');
        }
        buf.append(']');
        return buf.toString();
    }

    public int compareTo(Object o) {
        OctopusDiscreteState other = (OctopusDiscreteState) o;
        for(int i = 0; i < discreteStates.length; ++i) {
            if(discreteStates[i] != other.discreteStates[i]) return discreteStates[i] - other.discreteStates[i];
        }
        return 0;
    }
}
