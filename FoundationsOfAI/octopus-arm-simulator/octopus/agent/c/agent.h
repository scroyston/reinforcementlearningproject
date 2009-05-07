#ifndef AGENT_H
#define AGENT_H

/* The first two variables indicate the size of the state_data and
 * returned action arrays respectively, these numbers are constant throughout
 * the length of the simulation.
 *
 * argc and agent_param are the remainder of the argv parameters passed on the
 * command line minus the first 4 (name of program, ip and port of
 * server and number of episodes)
 */
void agent_init(int num_state_variables, int num_action_variables, int argc, const char *agent_param[]);

/* When this function is called, the agent should return it's name. This
 * name will be used to identify individual agents during the competition.
 * The name should not contain any spaces nor linefeed characters.
 */
const char* agent_get_name();

/* Start an episode.
 * The agent is given the initial state in state_data.
 * The agent returns the action to take, the returned pointer will not be freed by the handler.
 * This pointer should be freed by the agent, if need be.
 * The size of these arrays has been specified in a prior call to agent_init.
 */
double* agent_start(double state_data[]);

/* Perform a single step of an episode.
 * The agent is given the reward for the previous action in reward.
 * The new state is given in state_data.
 * The agent returns the action to take, the returned pointer will not be freed by the handler.
 * This pointer should be freed by the agent, if need be.
 * The size of these arrays has been specified in a prior call to agent_init.
 */
double* agent_step(double state_data[], double reward);

/* The agent has reached a terminal state, indicating the end of an episode.
 * The agent is given the final reward for the previous action in reward.
 */
void agent_end(double reward);

/* The program will close so the agent should free any resource it has allocated
 */
void agent_cleanup();

#endif
