package rlframework;

import java.util.List;

public class EpsilonGreedySelector implements DiscreteActionSelector {

    private double epsilon;
    private StateActionValues qValues;
    private DiscreteActionEnumerator enumerator;
    
    public EpsilonGreedySelector(double epsilon, StateActionValues qValues, DiscreteActionEnumerator enumerator) {
        this.epsilon = epsilon;
        this.qValues = qValues;
        this.enumerator = enumerator;
    }

    public DiscreteAction getAction(DiscreteState s) {
        DiscreteAction a = null;
        if(Math.random() > this.epsilon) {
            // We take the best action
            a = qValues.getBestAction(s);
        }
        // It is possible that no best action was defined, so we need to check
        // if there is no action defined, we choose randomly
        if(a == null) {
            // We choose randomly between all actions
            List<DiscreteAction> actionList = enumerator.getAvailableActions(s);
            a = actionList.get((int)(Math.random()*actionList.size()));
        }
        return a;
    }
    
    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }
    
    public double getEpsilon() {
        return this.epsilon;
    }

}
