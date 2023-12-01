/**
* Domain classes used to manipulate the database directly. They are actually not exactly classes. 
* They are interfaces that extend CRUD. 
* <p>
* These classes contain simple functions to manipulate the database. It does not contain any logic. 
* functions can be added to select() or find() by different variables.
* See the entity package for the definitions of each entity used by the program.
* </p>
*
* @since 1.0
* @author Niels de Bruin
* @version 1.0
*/

package nl.cfns.repository;

//package for repositories to manipulate database. only contains simple save, delete and search by functions