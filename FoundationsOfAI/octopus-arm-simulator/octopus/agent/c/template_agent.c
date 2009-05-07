#include <stdlib.h>
#include <time.h>

#include "agent.h"

/**
 * This agent is a good base to implement an agent. It stores the number of 
 * state variables in the environment and the number of actions to perform
 * on each step.
 * 
 * It returns a valid name containing only alpha numeric characters and 
 * underscores.
 * 
 * At every step it returns a new set of random actions.
 * 
 * Note that no function can fail here, so the agent always returns 0, however
 * When an error occurs, the agent should return -1.
 */
int num_states=0, num_actions=0;

void update_action(double out_action[]);

double* actions;

void agent_init(int num_state_variables, int num_action_variables, int argc, const char *agent_param[]) {
	num_states = num_state_variables;
    num_actions = num_action_variables;
    actions = (double*)malloc(sizeof(double)*num_actions);
	srand( time(NULL) );
}

const char* agent_get_name() {
	return "C_Random";
}

double* agent_start(double state_data[]) {
    update_action(actions);
    return actions;
}

double* agent_step(double state_data[], double reward) {
    update_action(actions);
    return actions;
}

void update_action(double out_action[]) {
    int i;
    for (i = 0; i < num_actions; ++i) {
    	out_action[i] = ((double)rand()) / RAND_MAX;
    }
}

void agent_end(double reward) {
}

void agent_cleanup() {
    free(actions);
}
