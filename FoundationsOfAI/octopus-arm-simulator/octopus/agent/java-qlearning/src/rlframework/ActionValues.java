package rlframework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActionValues implements Serializable {
    private static final long serialVersionUID = -6493167945002759872L;
    
    private Map<DiscreteAction, Double> values;
    
    public ActionValues() {
        values = new HashMap<DiscreteAction, Double>();
    }

    public double getValue(DiscreteAction a) {
        if(values.containsKey(a)) {
            return values.get(a);
        } else {
            return 0;
        }
    }
    
    /**
     * Returns the action associated with the highest value.
     * 
     * @return The best action that has been seen yet, or null if there is no action
     * defined.
     */
    public DiscreteAction getBestAction() {
        DiscreteAction action = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        
        for(Map.Entry<DiscreteAction, Double> e: values.entrySet()) {
            if(e.getValue() > bestValue) {
                bestValue = e.getValue();
                action = e.getKey();
            }
        }
        
        return action;
    }
    
    public void updateValue(DiscreteAction a, double value) {
        values.put(a, value);
    }
    
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(Map.Entry<DiscreteAction, Double> e: values.entrySet()) {
            buf.append('(').append(e.getKey()).append(")").append(e.getValue()).append(' ');
        }
        return buf.toString();
    }

}
