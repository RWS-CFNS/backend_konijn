/**
* Domain classes used define entities as used by the program. These classes also define values for verification.
* Database tables are created using these classes. 
* Lombok is used to simplify the creation of constructors, getters, setters and verification. Lombok has to be manually connected to the Eclipse IDE
* See documentation for variable validation values
* 
* IMPORTANT: making changes here creates conflicts with the database. Make a backup to prevent data loss. 
* To implement database changes when testing, you may have to delete the tables using the H2 console
* 
*
* @since 1.0
* @author Niels de Bruin
* @version 1.0
*/

package nl.cfns.entity;

//package for repositories to manipulate database. only contains simple save, delete and search by functions