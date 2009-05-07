import java.io.Serializable;

import rlframework.DiscreteAction;

public class OctopusDiscreteAction implements DiscreteAction, Comparable, Serializable {
    private static final long serialVersionUID = 3244921571706909322L;
    
    private double[] action;
    private String name;
    private int hash;
    
    public OctopusDiscreteAction(double[] action, String name) {
        this.action = action;
        this.name = name;
        hash = 0;
        for(int i = 0; i < action.length; ++i) {
            hash *= 7;
            hash += (action[i]*7);
        }
    }
    
    public double[] getAction() {
        return action;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OctopusDiscreteAction) {
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
        return name;
    }

    public int compareTo(Object o) {
        OctopusDiscreteAction other = (OctopusDiscreteAction)o;
        for(int i = 0; i < action.length; ++i) {
            if(action[i] < other.action[i]) return -1;
            else if(action[i] > other.action[i]) return 1;
        }
        return 0;
    }
}
