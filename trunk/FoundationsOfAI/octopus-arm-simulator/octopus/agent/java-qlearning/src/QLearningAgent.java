import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rlframework.EpsilonGreedySelector;
import rlframework.QLearning;
import rlframework.StateActionValues;
import rlframework.StateDiscretizer;

public class QLearningAgent implements Agent {
    private int numCompartments;
    private int numSteps;
    private int numEpisodes;

    private StateActionValues qValues;
    private QLearning learner;
    private StateDiscretizer discretizer;
    private String qValuesFileName;
    
    /**
     * Provides the task specification to the agent and initializes the agent.
     * This method is called once at the start of each session, and should be
     * used to perform any one-time initialization tasks, such as constructing
     * data structures or reading training data from a file. The last parameter
     * is given the value of the "-q" or "--agent-param" option that was passed
     * at the command line. It can be used to provide additional custom
     * initialization information to the agent, such as the name of a file to
     * read training data from. It will be null if nothing was supplied.
     *
     * This method must return a name for the agent, which is used to
     * identify the agent in log files produced by the environment. The name
     * must contain only letters, digits, and underscores.
     *
     * @param stateSize the dimensionality of the state space.
     * @param actionSize the dimensionality of the action space.
     * @param param The name of a file in which it will load/store the Q-Values, if none is provided, they will not be saved.
     * @return the agent's name.
     */
    public String init(int stateSize, int actionSize, String param) {
        this.numSteps = 0;
        this.numEpisodes = 0;
        this.numCompartments = (actionSize-2)/3;
        
        qValuesFileName = param;
        
        File in = new File(qValuesFileName);
        if(in.canRead()) {
            try {
                this.qValues = (StateActionValues) new ObjectInputStream(new FileInputStream(in)).readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.qValues = new StateActionValues();
        }
        
        this.learner = new QLearning(qValues, new EpsilonGreedySelector(0.1, qValues, new OctopusActionEnumerator(numCompartments)));
        this.discretizer = new TipAngleStateDiscretizer(stateSize);
        
        return "SimpleQLearningAgent";
    }
    
    /**
     * Performs and returns the first action of an episode.
     *
     * @param initialState the initial state of the episode.
     * @return the agent's first action.
     */
    public double[] start(double[] initialState) {
        numEpisodes++;
        numSteps = 1;
        
        OctopusDiscreteAction a = (OctopusDiscreteAction) learner.start(discretizer.discretize(new OctopusContinuousState(initialState)));
        return a.getAction();
    }
    
    /**
     * Performs one step of the agent.
     *
     * @param state the current state.
     * @param reward the reward resulting from the agent's last action.
     * @return the agent's next action.
     */
    public double[] step(double[] state, double reward) {
        numSteps++;
        
        OctopusDiscreteAction a = (OctopusDiscreteAction) learner.step(discretizer.discretize(new OctopusContinuousState(state)), reward);
        return a.getAction();
    }
    
    /**
     * Processes the final reward of an episode.
     *
     * @param the final reward of the episode.
     */
    public void end(double reward) {
        learner.end(reward);
    }
    
    /**
     * Performs any finalization tasks, such as releasing resources or
     * writing training data to a file. This method is called once at the end
     * of each session.
     */
    public void cleanup() {
        System.out.println(qValues);
        if(qValuesFileName.length() > 0) {
            try {
                new ObjectOutputStream(new FileOutputStream(qValuesFileName)).writeObject(qValues);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
