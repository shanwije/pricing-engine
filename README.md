#Pricing Engine

to run application in local please have docker installed in your system ( latest version is prefered )
Run the following commands in sequence and use the health-end point in browser or on postman to see 
whether the service is up and `8080` is exposed to the local env.
####in application root
####run`./mvnw install`
####`docker-compose build` ( this will build the application image )
####`docker-compose up` ( this will run the application image and db )

at the end of the testing, using the below to clean up
####`docker-compose down`
    
apart from that you can also run the application in conventional manner

###Health end-point : `/actuator/health`
###products-and-prices end-point : `/products-and-prices`





###initial notes
20 of Penguin-ears = Carton, Carton of penguin-ears is $20;
5 of Horseshoe = Carton, Carton of Horseshoe is 825

-- single unit
1 penguin-ear = Carton of penguin-ears price * 130% / 20
1 horseshoe = Carton of Horseshoe price * 130% / 5

--if more than 2 cartons,
    Carton of penguin-ears = Carton of penguin-ears price * 90%
    Carton of Horseshoe = Carton of Horseshoe price * 90%

