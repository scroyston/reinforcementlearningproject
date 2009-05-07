
               Octopus Arm Simulator
               =====================

Last changed: 2009-03-03

Contents:
=========

     I. OVERVIEW AND REQUIREMENTS

    II. WRITING AND COMPILING YOUR AGENT

   III. RUNNING THE ENVIRONMENT

    IV. COMMUNICATION PROTOCOL DOCUMENT 

     V. ENVIRONMENT PHYSICS DOCUMENT
     
    VI. LOG OF THE REWARDS OBTAINED

   VII. KNOWN BUGS (none currently)


============================
I. OVERVIEW AND REQUIREMENTS
============================

This document should help you write and compile an agent that will run in the octopus environment.

The environment is written in Java and communicates with the agent over sockets, using a protocol following the lines of the RL-Glue concepts. In principle, this allows agents to be written in any language and run on any platform. Familiarity with socket programming is not required to implement an agent; for a few commonly used languages, we supply "agent handlers" that act as intermediaries between your agent and our environment, providing abstraction from the details of the socket communication.

Running the environment requires version 6 or later of the Java Runtime Environment (JRE). To check the version of the JRE installed on your system, type "java -version" at a command prompt.  If you do not have the JRE, or do not have the correct version, you can download it free of charge by going to http://java.sun.com/javase/downloads/?intcmp=1281, clicking on the "Download" link for JRE 6 Update 12, and then following the instructions presented. (The update number may be higher by the time you read this.) On some GNU/Linux distributions, you may also be able to obtain the JRE using the distribution's software package management system; consult its documentation for details.


====================================
II. WRITING AND COMPILING YOUR AGENT
====================================

Currently, our agent handlers support C (and C++ by extension), Java and Python. To be work with the handler, your agent must conform to an informally specified interface, which is described in a separate document available at the distribution site. For convenience, we provide a "template agent", which performs, in each supported language; you can modify one of these to implement your agent.


II.A   C Agents
---------------

In C, all agents must implement the function prototypes given in agent.h, located in "agent/c" directory of the distribution. You will also find a template agent, template_agent.c, which you can modify to write your agent.

The provided Makefile was tested on linux and windows (cygwin). We also provided a Makefile.solaris if your uname is SunOS. If there are a lot of requests, we may provide a Windows (Visual Studio) version.

To run the agent, you need to specify the host, port, number of episodes and (optionaly) parameters that will be used by your agent. For example:

./agent_handler localhost 10000 10

Type "./agent_handler" with no additional arguments for a description of available options.


II.B   Java Agents
------------------

In Java, all agents must implement the Agent interface; consult the template agent, contained in the file RandomAgent.java in the "agent/java" directory of the distribution, for a list and description of the methods in the interface. When compiling your agent, you must include java-agent-handler.jar (also in the "java" directory) on the classpath when compiling your agent. For example:

javac -cp JavaAgentHandler.jar MyAgent.java

Note that there is no need to open JavaAgentStub.jar, or to compile any source files other than those of your agent.

Once you have compiled your agent and started the environment, you can run it by executing JavaAgentHandler.jar, providing the class name of your agent and the environment's port as command-line arguments. For example:

java -jar JavaAgentHandler.jar -a MyAgent -p 10000

Type "java -jar JavaAgentHandler.jar" with no additional arguments for a description of available options. The -c (agent classpath) option is especially useful if your agent is packaged in a JAR file, or uses additional libraries.


II.C	 Python agents
---------------------

In Python, all agents must implement the public (i.e. those not starting with "__") methods of the template agent, agent.py, which you can find in the "agent/python" directory of the distribution. To run the agent, you need to call agent_handler.py, which will handle the communication with the environment and call the appropriate methods of your agent. For example:

python agent_handler.py 127.0.0.1 10000 1

Type "python agent_handler.py" with no additional arguments for a description of available options.


============================
III. RUNNING THE ENVIRONMENT
============================

To run the environment, execute octopus-environment.jar, located in the "environment" directory of the distribution. The command line syntax is as follows:

java -jar OctopusEnvironment.jar <exec. mode> <settings file> <state file> [port number]

Example:

java -jar OctopusEnvironment.jar external_gui settings.xml states.xml 10000


The <exec. mode> parameter determines how the simulator will be controlled, and whether a graphical display is provided. It must be one of the following:

  - "internal": you control the octopus arm with the keyboard, and can graphically see the environment state.
  - "external_gui": your agent controls the arm, and you can graphically see the environment state.
  - "external": your agent controls the arm, and no graphical output is given. This is the preferred mode for agent learning.

The <settings file> parameter must point to an existing XML settings file, which  specifies the agent's task, the properties of the arm, and various physical parameters. A recommended settings file is supplied in the root folder of the distribution.

The <state file> parameter specifies the name of a file that contains the states to be used as initial states of episodes. States can be added to the file using the "state dump" function available with internal control. If the named file does not exist, it will be created.

Note that each state file is coupled to the settings file that was used when it was created. If you modify the settings file, you must create a new state file; attempting to use the old one will produce errors.

The optional [port number] parameter specifies the port on which the simulator accepts connections from external agents. If not given, it defaults to 10000. (Your network and operating system may impose restrictions on what ports you can successfully use. Make sure that your OS allows you to start a server on the port you specify, and make sure the port is open to the agent that will connect to it, particularly if the agent and environment are not co-located.)


III.A  Internal control
-----------------------

This mode is useful for experimenting with the environment's dynamics. Only a limited subset of the (continuous) action space is available with internal control. Specifically, eight keys are mapped to different "primitives" from which actions can be constructed:

 - shift: rotate the base of the arm counter-clockwise
 - enter: rotate the base of the arm clockwise
 - Z: fully contract all dorsal muscles on the lower half of the arm
 - X: fully contract all transversal muscles on the lower half of the arm
 - C: fully contract all ventral muscles on the lower half of the arm
 - I: fully contract all dorsal muscles on the upper half of the arm
 - O: fully contract all transversal muscles on the upper half of the arm
 - P: fully contract all ventral muscles on the upper half of the arm
 
Multiple keys may be pressed simultaneously, allowing for a total of 2^8 discrete actions.

Additionally, some special controls are available:

  - space: start a new episode
  - D: dump the current state to the state file specified at the command line. The state will not actually be used to begin episodes until the next session.


III.B  External control with graphical display
----------------------------------------------

This mode is useful to visually validate what your agent is doing, but it is unsuitable for learning, as the simulation speed is reduced by graphical rendering as well as delays between animation frames. Use the next mode for learning.


III.C  External control without graphical display
-------------------------------------------------

This mode is preferred when agents are learning, since the simulation occurs at maximum speed. To stop the environment, you must forcibly terminate its process using the functions provided by your operating system.


===================================
IV. COMMUNICATION PROTOCOL DOCUMENT
===================================

A document describing the protocol of communication between the agent handler and the environment is available at the distribution site. Note that if you are using the handlers we provided, you do not need to read this document.


===============================
V. ENVIRONMENT PHYSICS DOCUMENT
===============================

A document explaining the environment's physics is available at the distribution site.


===============================
VI. LOG OF THE REWARDS OBTAINED
===============================

For benchmarking purposes, at the end of each session, the environment writes out a log file containing the reward obtained at every timestep during the session. The file is named using the agent's name and the date/time that the session started. Inside the file, each episode is on one line, with the rewards at each time step separated by spaces.


===============
VII. KNOWN BUGS
===============

There are no known bugs as of 2009-03-03. If you notice any unexpected behaviour, please report to dan.cast@mail.mcgill.ca.
