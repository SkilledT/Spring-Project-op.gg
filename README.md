Technology in use : [Java, Spring Boot, React.js, HTML5, CSS, Bootstrap, SQL, T-SQL, JPA, Junit, Mockito]
The aim of the project to build an application to analyze matches in League of Legends, statistical data about champions, their builds, items, perks and so on. The application provides insight of player's performance in general (overall performance) and in every game.

![](./pictures/dashboard.PNG)
![](./pictures/champion_section.PNG)
#champion_card_picture

How does it work?
All the data is retrieved from Riot's API. In the matter of a Riot's API limitations (200 requests per minute) all the data is being stored in database. Due to this reason the data to frontend is taken from database and have to manually refreshed by user using button. Moreover NOT all the data is avaiable - just last 50 matches. That's additional the reason why we have to store the data. We can't come to any valuable conclusions having not enough data.
In general application is suppose to be supportive in a process of decision-making. Since Riot doesn't provide statistical data - just 'basic' information about match, we need to prepare data. 

End-points / Current Feature:
	Match:
		GET /api/v1/match/championStats/{nickname} - returns DTOs with champion performances for a summoner
		GET /api/v1/match/details/{nickname} - returns DTOs with last matches
		GET /api/v1/match/rolePreferences/{nickname} - prefered role based on last games
		GET /api/v1/match/refresh/{nickname} - refreshes avaiable champions
		GET /api/v1/match/refresh/challengers - refreshes data collection with challengers matches
	Perk:
		GET /api/v1/perk/mainTree/{championName} - returns DTO with most popular perk tree based on last matches
		GET /api/v1/perk/subTree/{championName} - returns DTO with most popular sub tree (based on main tree) based on last matches
		GET /api/v1/perk/refresh - refreshes data about available perk trees
	Item:
		GET /api/v1/item/refresh - refreshes data about avaiable items
		GET /api/v1/item/mostPopular/{championName} - returns most popular Items for a champion (always returns boots (one pair))
	Summoner:
		GET /api/v1/summoner/{nickname} - returns DTO with needed summoner data
		GET /api/v1/summoner/league/{nickname} - returns DTO with needed summoner's league
	Champion:
		GET /api/v1/champion/refresh - refreshes avaiable champions
		GET /api/v1/champion/strongAganist/{championName} - returns 3 champions that has lowest win ratio aganist chosen champion

Future feature:
> Security
> Graphs with performance based on chosen role and related matches (Gonna try out Strategy pattern)