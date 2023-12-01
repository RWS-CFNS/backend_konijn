/**
* Domain classes used to produce simulator classes that send data from the backend to the rabbitMQ server.
* This back-end system will then consume the messages from the rabbitMQ server.
* An isSimulated variable, contained inside each object, is used to tell apart which data entries are real or simulated.
* <p>
* The classes are:
* AsyncDemoBeans -> demonstrates asynchronus functionality
* DataSimulator -> generates data of different types
* MessageSimulator -> send simulated data to the rabbitMQ server
* SimulatorConfig -> set simulator configurations
* </p>
*
* @since 1.0
* @author Niels de Bruin
* @version 1.0
*/


package nl.cfns.simulate;