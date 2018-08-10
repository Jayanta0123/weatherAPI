Create schema weatherApp ;


Use weatherApp ;
Create table CityDetails (
	id		bigint,
    	createdAt	datetime,
    	createdBy	varchar(50),
    	cityName	varchar(500),
    	state		varchar(50),
    	country		varchar(20)    
) ;

