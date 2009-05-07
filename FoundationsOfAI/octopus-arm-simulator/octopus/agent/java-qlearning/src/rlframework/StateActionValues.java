package rlframework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a table of Q-values or State-Action values, because it is a table
 * it is only valid with discrete states and discrete actions.
 *
 */
public class StateActionValues implements Serializable {
    private static final long serialVersionUID = -8726482198145020323L;
    
    private Map<DiscreteState, ActionValues> qValues;
    
    public StateActionValues() {
        qValues = new HashMap<DiscreteState, ActionValues>();
    }
    
    public ActionValues getActionValues(DiscreteState s) {
        if(qValues.containsKey(s)) {
            return qValues.get(s);
        } else {
            ActionValues av = new ActionValues();
            qValues.put(s, av);
            return av;
        }
    }
    
    public DiscreteAction getBestAction(DiscreteState s) {
        return getActionValues(s).getBestAction();
    }

    public double getValue(DiscreteState s, DiscreteAction a) {
        return getActionValues(s).getValue(a);
    }
    
    public void updateValue(DiscreteState s, DiscreteAction a, double value) {
        getActionValues(s).updateValue(a, value);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(Map.Entry<DiscreteState, ActionValues> e: qValues.entrySet()) {
            buf.append(e.getKey()).append(": ").append(e.getValue()).append('\n');
        }
        return buf.toString();
    }
    
}
