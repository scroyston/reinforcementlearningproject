import rlframework.DiscreteState;
import rlframework.State;
import rlframework.StateDiscretizer;

public class TipAngleStateDiscretizer implements StateDiscretizer {
    
    public TipAngleStateDiscretizer(int numStates) {
    }

    public DiscreteState discretize(State s) {
        if(s instanceof DiscreteState) {
            return (DiscreteState) s;
        } else if(s instanceof OctopusContinuousState) {
            OctopusContinuousState state = (OctopusContinuousState)s;
            OctopusNodeState nodeState = state.getNodeState(state.getNumberOfNodes()-1);
            double x = nodeState.getX();
            double y = nodeState.getY();
            // convert to polar coordinates (with angle between 0 and 1)
            double angle = (Math.atan2(y,x) + Math.PI)/(2*Math.PI);
            double radius = Math.sqrt(x*x + y*y);
            int[] discrete = new int[2];
            discrete[0] = (int)(60*angle); // 60 divisions for the angle
            discrete[1] = (int)(radius * 2);
            return new OctopusDiscreteState(discrete);
        }
        return null;
    }

}
