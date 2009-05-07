import java.util.ArrayList;
import java.util.List;

import rlframework.DiscreteAction;
import rlframework.DiscreteActionEnumerator;
import rlframework.State;

public class OctopusActionEnumerator implements DiscreteActionEnumerator {
    List<DiscreteAction> actions;
    
    public OctopusActionEnumerator(int numCompartments) {
        int numActions = 2+3*numCompartments;
        
        actions = new ArrayList<DiscreteAction>();
        double[][] actionArray = new double[7][numActions];
        
        // First Action: no action
        // Second Action: rotate cw
        actionArray[1][0] = 1;
        // Third: rotate ccw
        actionArray[2][1] = 1;
        
        for(int i = 0; i < numCompartments; ++i) {
            // Fourth: curl ccw
            actionArray[3][3*i+2] = 1;
            // Fifth: stretch
            actionArray[4][3*i+3] = 1;
            // sixth: curl cw
            actionArray[5][3*i+4] = 1;
            // seventh: compress
            actionArray[6][3*i+2] = 1;
            actionArray[6][3*i+4] = 1;
        }
        actions.add(new OctopusDiscreteAction(actionArray[0], "Nothing"));
        actions.add(new OctopusDiscreteAction(actionArray[1], "Rotate CCW"));
        actions.add(new OctopusDiscreteAction(actionArray[2], "Rotate CW"));
        actions.add(new OctopusDiscreteAction(actionArray[3], "Curl CCW"));
        actions.add(new OctopusDiscreteAction(actionArray[4], "Stretch"));
        actions.add(new OctopusDiscreteAction(actionArray[5], "Curl CW"));
        actions.add(new OctopusDiscreteAction(actionArray[6], "Compress"));
    }

    public List<DiscreteAction> getAvailableActions(State s) {
        return actions;
    }

}
