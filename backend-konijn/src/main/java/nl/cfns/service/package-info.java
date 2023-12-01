/**
* Domain classes used to produce responses to other systems. Heatmaps are generated in this package
* <p>
* These classes contain logic for controllers. Implementation of the service layer. Uses repositories to manipulate database
*  The classes are:
*  CommunicationHandler -> send messages to the rabbitMQ server. Usually these are requests for measuringboxes
*  HeatmapGenerator -> takes lists and other parameters to generate a heatmap. Either a normal or a hex grid is used, depending on future development of the project
*  Receiver -> consumes rabbitMQ messages. This only succeeds if (1)a JSON format is used (2) corresponding headers/properties are included
*  SignalstrengthService -> controls when heatmaps should be generated, filtering and also interacts with reposititories for fetching entries
* </p>
*
* @since 1.0
* @author Niels de Bruin
* @version 1.0
*/

package nl.cfns.service;
