package rlframework;

public class QLearning {
    private final static double ALPHA = 0.3;
    private final static double GAMMA = 0.9;
    
    StateActionValues qValues;
    DiscreteActionSelector actionSelector;
    
    DiscreteState lastState;
    DiscreteAction lastAction;
    
    public QLearning(StateActionValues qValues, DiscreteActionSelector actionSelector) {
        this.qValues = qValues;
        this.actionSelector = actionSelector;
        
        this.lastState = null;
        this.lastAction = null;
    }
    
    public DiscreteAction start(DiscreteState s) {
        DiscreteAction a = actionSelector.getAction(s);
        lastState = s;
        lastAction = a;
        return a;
    }

    public DiscreteAction step(DiscreteState s, double reward) {
        DiscreteAction a = actionSelector.getAction(s);
        updateQValue(lastState, lastAction, reward, qValues.getValue(s, a));
        lastState = s;
        lastAction = a;
        return a;
    }
    
    public void end(double reward) {
        updateQValue(lastState, lastAction, reward, 0);
    }
    
    private void updateQValue(DiscreteState s, DiscreteAction a, double reward, double nextValue) {
        double val = qValues.getValue(s, a);
        val = val + ALPHA*(reward + GAMMA*nextValue - val);
        qValues.updateValue(s, a, val);
    }
}
