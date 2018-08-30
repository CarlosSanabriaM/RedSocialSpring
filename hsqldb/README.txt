To start de database, place yourself in the bin folder, and execute the runServer file (.bat file for Windows and .sh file for Mac or similar). In Mac you will have to place yourself in that folder in the command line and execute it like this: "./runServer.sh", without the double quotes. In Windows you'll only need to double click the file in the Folder Explorer.

It will create some files in the bin folder to store the data.

---------------------------------------------------------
Complete files and folders explanation:
* bin/: Contains 4 files (2 for Windows (.bat) and 2 for Mac or similar (.sh)):
        -runServer: Starts the database, which listens by default on the port 9001.
        -runManagerSwing: Opens a GUI to visualize the database. It allows you to execute SQL commands, queries, ...

* lib/: Contains 2 .jar files, needed for the database. You don't need to do anything with them.

* data/: This folder only contains one file at the beginning (.gitignore). More files will be created when you start the database. Here is where the database info will be stored. The name of the database will be "test".
        -.gitignore: This file is used for the version control with git. It allows to keep the data folder in the version control without keeping also the rest of the files of that folder (they shouldn't be in the version control because they represent the specific state of the database).
        ----------------------------------------------------------
        -test.properties: Contains a few settings about the database.
        -test.script: Contains the definition of tables and other database objects, plus the data for non-cached tables.
        -test.log: Contains recent changes to the database.
        -test.data: Contains the data for cached tables.
        -test.backup: Is a compressed backup of the last known consistent state of the data file.

	---All these files are essential and should never be deleted---
        ----------------------------------------------------------
        -test.lck: Is also used to record the fact that the database is open. This is deleted at a normal SHUTDOWN.
