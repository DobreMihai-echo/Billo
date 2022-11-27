# Billo

Billo is a web-based system which allows consumers to pay bills smoothly without involvement from third-party platforms. The current platforms of providers will be replaced with our solution that will act as a single source of truth for all the payments made by a person.

# Billo project requirements
1. You need to have NodeJS and NPM installed
2. Have PostgreSQL installed on your machine
3. Have an IDE installed (example: Intellij for backend, Visual Studio Code for frontend), whatever works for you best 

# Billo project requirements rundown
1. You need to have NodeJS and NPM installed
- Download NodeJS from https://nodejs.org/en/
- Run the installer and follow the steps
- You will need to restart your machine for the installation to complete
- Now test that everything in installed( "node -v" for NodeJS Version and "npm -v" for npm version)

2. Have PostgreSQL installed on your machine
- Download PostgreSQL installer from https://www.postgresql.org/
- Run the installer and follow the steps (make sure you remember the username, password and port you set)
- You will need to restart your machine for the installation to complete
- Now open "SQL Shell (psql)
- You will be prompted to enter the server (the default is localhost so press enter)
- Secondly you will be prompted to enter the database (the default one is postgresql so press enter)
- Thirdly you will be prompted to enter the port (the default port should be 5432 if you didn't changed it, press enter if you didn't change the port during installation, or type in the port you set followed by enter)
- Fourthly you will be prompted to enter the username (if you didn't changed the username during installation press enter, otherwise type the username you set followed by enter)
- Lastly you will be prompted to type in the password
- After this steps you should be able to access PostgreSQL
- Type the following command "CREATE DATABASE test" (Make sure
- This will create a database with the name test (we will use this for setting up the Backend server)

# Billo backend server start
When you clone the repository, you will have 2 folders. Open the "backend" folder with your IDE (preferable Intellij). You will need to navigate to src -> main -> resources. Right click on "resources" folder, go to new -> file and then type "application properties". A new file will be created. Copy the content from the "application.properties.model" to your "application.properties" file. 
spring.datasource.url should have the server(which should be localhost), followed by the port(default is 5432, if you changed the port during PostgreSQL, make sure you type that port instead of the 5432), and the database should be "test" (the one you created during the PostgreSQL installation guide (you can use whatever database you wish). Make sure the username and the password are corect. Then you can navigate to src -> main -> java -> com.billo.backend and you can start the Backend application.

# Billo frontend start
When you clone the repository, you will have 2 folders. Open the "frontend" folder with your IDE (preferably Visual Studio Code). You will need to type "npm install" for the node_modules to be downloaded. After this step is complete, type "npm start" for the frontend application to start.

https://www.figma.com/file/8oclQmxz7Ym3nzd7FudWVM/Billo?node-id=0%3A1&t=lx3So1qbNAZC6G72-0

# Billo API Documentation using Swagger Hub
You can find the Public Api Documentation by accessing the following link: https://app.swaggerhub.com/apis/MIHAIDOBRE1807/Billo_Public_Api/1.0.0
