# Getting Started

### What this is

This is a simple template to facilitate the development startup of our Backend Challenge.
Provided in this project is a stripped down Springboot application, with a Postgres database, orchestrated with Docker. 

### Requirements

 - Maven
 - Docker

### Start-up

Run the following commands in the command line:

    - mvn clean install 
    - docker build -t challenge-1.0.0.jar .
    - docker-compose up

In the case of the docker compose fail:

    - Stop the container 
    - Start again
    In my machine spring sometimes would start before the database 
    finish. 
    So Spring would try to establish a connection to an ongoing deploy of 
    postgreSQL database

In the path **curlFiles/curlFiles.txt** there are all the CURL files for 
running the APIs that this project exposes. 
They were automatically exported by Postman

**Some Considerations:** 
I overly simplified the Appointment model in the database, simply
because of the time that I had to implement this project. 
There are many improvements that I know that can be done,
the code could be more structured, UnitTests are missing 
and so is the logging.
Using a HashMap to manipulate the JSON output is obviously a hammer
Using a custom Page class instead of the JPA for instance was not the best approach
but since I simplified the database model there is more load 
on the Java side to manipulate the data which in terms of performance 
is not great. 
This could be improved by improving the database model and improving the 
paginated functionality with JPA, this way the data would come from the database
already paginated and hence improving the performance. 
The way I build it was to fetch all data from the database, manipulate
it to get the structure that I wanted and then Paginate it. If I have thousands
or more rows in the DB with this pagination solution I may take time that I would not
need taking with a different approach. 
