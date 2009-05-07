package rlframework;

import java.util.List;

public interface DiscreteActionEnumerator {
    public List<DiscreteAction> getAvailableActions(State s);
}
