# News Scraper Search Engine

This project scraps the articles data from https://www.thehindu.com/archive/ and stores them in a json file. The scraped data is then sent to Solr server for indexing. There is a Spring Boot Application containing APIs to perform search on the indexed data. 

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
What things you need to install the software and how to install them

#### Scraping
Data Scraping is done with Scrapy, which is a Python based crawling framework, used to extract the data from the web page with the help of selectors based on XPath.

To install Python, follow - https://www.python.org/downloads/

To install scrapy after installing Python, use -

```
pip install scrapy
```

Once scraping is done, the scraped data is stored in a json file. This scraped data is sent to Solr using Pysolr.
To isntall Pysolr, use -
```
pip install pysolr
```

### Searching
Seaching is done using Solr Search Engine. Solr is a scalable, ready to deploy, search/storage engine optimized to search large volumes of text-centric data. Solr is enterprise-ready, fast and highly scalable. 
To install Solr, follow - http://lucene.apache.org/solr/
Once installed, start the server by running the following command in bin folder of solr -

```
./solr start
```

create a Solr Core, named articles using -

```
./solr create_core -c articles
```

Copy and replace the folder conf inside solrCore to the following folder - solr-7.6.0/server/solr/articles/

OR

Replace the managed-schema and solrconfig.xml files inside solr-7.6.0/server/solr/articles/conf/ with the managed-schema and solrconfig.xml files in the solrCore/conf folder of the repository.

### API
The Spring Boot Appplication is named NewsScraperSearch, to run this application,
Java (https://www.oracle.com/technetwork/java/javase/downloads/index.html) and 
Maven (https://maven.apache.org/download.cgi) 
is needed, which can be installed by following the mentioned links.

## Running the tests
To start the scraper, open a terminal/cmd in the newsScraper folder(which contains scrapy.cfg file) and run -
```
scrapy crawl thehindubot -o items.json -t json
```
The scraper create a items.json file at the same location. The json file contains a list of following object -

```
{
    "url": "https://www.thehindu.com/sport/other-sports/kore-stuns-deepan-in-joint-lead/article2790567.ece",
    "title": "Kore stuns Deepan, in joint lead",
    "description": "IM Akshayraj Kore shocked GM Deepan Chakkravarthy to join the leaders at the end of the ninth round in the SDAT-RMK Chennai Open international Grandmaster chess tournament here on Tuesday.",
    "author": "Arvind Aaron"
  }
```

To send the scraped data to Solr server, run the following in the same terminal/cmd -

```
python inject.py items.json http://{Solr Server IP}:8983/solr/articles
```
Replace {Solr server ip} in the above command with the ip address of the Solr server

If the Solr server and the Application Server are on different machines, then the following property needs to be changed in the application.properties file, located in  NewsScraperSearch/src/main/resources/application.properties - 

```
solr.server.articles.url
```

To start the Application Server, go to the NewsScraperSearch folder, open a terminal/cmd and run the following -

```
mvn clean install
cd target/
java -jar NewsScraper-0.0.1-SNAPSHOT.jar
```

Once the server is started, following APIs can be used for testing:


Explain how to run the automated tests for this system

### Author Search
This API searches the list of authors on the basis of suppplied search query
API Endpoint - {server IP}:8081/newsScraper/author/search?author=searchQuery
Method - GET
Sample Request - http://localhost:8081/newsScraper/author/search?author=ap
Sample Response -

```
{
    "authors": [
        {
            "author": "AP"
        },
        {
            "author": "Rajulapudi Srinivas"
        },
        {
            "author": "Vijay Lokapally"
        }
    ]
}
```

author parameter is a mandatory field

### Article Search
This API searches articles in Solr on the basis of supplied search parameter.
Method - GET
There are 3 search parameters -
author
title
description
Exactly one of these parameter is required in the request
#### Search Based on Author Name
API Endpoint - {server IP}:8081/newsScraper/article/search?author=searchQuery
Sample Request - http://localhost:8081/newsScraper/article/search?author=ap
Sampel Response -

```
{
    "articles": [
        {
            "url": "https://www.thehindu.com/news/international/bomb-kills-20-in-northwest-pakistan/article2789825.ece",
            "title": "Bomb kills 20 in northwest Pakistan",
            "author": "AP",
            "description": "A bomb exploded close to a bus in northwest Pakistan on Tuesday, killing 20 people in the deadliest blast in the country in several months, a government official said."
        },
        {
            "url": "https://www.thehindu.com/news/international/us-womans-quest-could-mean-medal-of-honour-for-dad/article2790546.ece",
            "title": "U.S. woman's quest could mean Medal of Honour for dad",
            "author": "AP",
            "description": "It was bravery at the highest level- William Shemin defied German machine gun fire to sprint across a World War I battlefield and pull wounded comrades to safety. And he did so no fewer than three times."
        },
        {
            "url": "https://www.thehindu.com/sport/football/platini-messi-needs-world-cup-to-be-the-greatest/article2790121.ece",
            "title": "Platini: Messi needs World Cup to be the greatest",
            "author": "AP",
            "description": "Michel Platini said Lionel Messi must win a World Cup with Argentina before he can be considered a contender as the greatest player of all time."
        },
        {
            "url": "https://www.thehindu.com/sport/football/fifa-pledges-to-protect-matchfixing-witnesses/article2790380.ece",
            "title": "FIFA pledges to protect match-fixing witnesses",
            "author": "AP",
            "description": "FIFA has pledged to protect witnesses who report match-fixing plots by organised crime syndicates."
        },
        {
            "url": "https://www.thehindu.com/opinion/op-ed/fake-bomb-smuggled-into-olympic-site/article2788319.ece",
            "title": "Fake bomb smuggled into Olympic site",
            "author": "AP",
            "description": "U.K. police managed to smuggle a fake bomb into Olympic Park in a security test, overshadowing a special Cabinet meeting on Monday at the park that marked 200 days until the Summer Games begin."
        },
        {
            "url": "https://www.thehindu.com/sport/cricket/bangladesh-cricket-ceo-passes-away/article2790500.ece",
            "title": "Bangladesh cricket CEO passes away",
            "author": "AP",
            "description": "The Bangladesh Cricket Board said chief executive officer Manzur Ahmed has died of a heart attack while asleep in his Dhaka apartment. He was 57."
        },
        {
            "url": "https://www.thehindu.com/sci-tech/technology/gadgets/latest-from-lg-tvs-you-can-talk-to-without-sounding-crazy/article2790225.ece",
            "title": "Latest from LG - TVs you can talk to, without sounding crazy",
            "author": "AP",
            "description": "Talking to the TV is usually a sign of extreme agitation, mental instability or loneliness. LG Electronics is set to make it a more rational behaviour this year, with a range of TVs that respond to speech."
        },
        {
            "url": "https://www.thehindu.com/sport/tennis/venus-williams-pulls-out-of-australian-open/article2789930.ece",
            "title": "Venus Williams pulls out of Australian Open",
            "author": "AP",
            "description": "Venus Williams has withdrawn from the Australian Open, prolonging her absence from the tennis tour because of an autoimmune disease that can cause fatigue and joint pain."
        },
        {
            "url": "https://www.thehindu.com/sport/tennis/wozniacki-kvitova-into-sydney-quarters/article2790114.ece",
            "title": "Wozniacki, Kvitova into Sydney quarters",
            "author": "AP",
            "description": "Top-ranked Caroline Wozniacki came back from 4-0 down in the final set to beat Dominika Cibulkova of Slovakia 7-5, 2-6, 6-4 on Tuesday and advance to the quarterfinals at the Sydney International."
        },
        {
            "url": "https://www.thehindu.com/news/cities/Vijayawada/cockfight-activity-begins-in-villages/article2789763.ece",
            "title": "Cockfight activity begins in villages",
            "author": "Rajulapudi Srinivas",
            "description": "Even as police has begun raids to prevent ‘cockfights', the activity has started in many villages ahead of ‘Sankranti' festival."
        },
        {
            "url": "https://www.thehindu.com/sport/cricket/keen-contest-on-the-cards/article2787841.ece",
            "title": "Keen contest on the cards",
            "author": "Vijay Lokapally",
            "description": "Rural face of Indian cricket; so be it! The Haryanvi cricketer is proud of playing for the State. Three quarterfinal spots in the last three years and the Ranji Trophy semifinal clash with Rajasthan at the Chaudhary Bansi Lal Stadium here from Tuesday places Haryana among the elite teams of domestic circuit."
        },
        {
            "url": "https://www.thehindu.com/sport/cricket/wickets-fall-like-nine-pins-harshal-wrecks-havoc/article2790582.ece",
            "title": "Wickets fall like nine pins; Harshal wrecks havoc",
            "author": "Vijay Lokapally",
            "description": "Rajasthan 89. Haryana 82 for eight. Not a T20 contest but just a day-old Ranji Trophy semifinal on a lively pitch that tested the character and discipline of a batsman."
        }
    ]
}
```

#### Search Based on Article Title or Description
API Endpoint - {server IP}:8081/newsScraper/article/search?title=searchQuery,
{server IP}:8081/newsScraper/article/search?description=searchQuery
Sample Request - http://localhost:8081/newsScraper/article/search?title=Bomb,
localhost:8081/newsScraper/article/search?description=exploded
Sample Response -

```
{
    "articles": [
        {
            "url": "https://www.thehindu.com/news/international/bomb-kills-20-in-northwest-pakistan/article2789825.ece",
            "title": "Bomb kills 20 in northwest Pakistan",
            "author": "AP",
            "description": "A bomb exploded close to a bus in northwest Pakistan on Tuesday, killing 20 people in the deadliest blast in the country in several months, a government official said."
        },
        {
            "url": "https://www.thehindu.com/opinion/op-ed/fake-bomb-smuggled-into-olympic-site/article2788319.ece",
            "title": "Fake bomb smuggled into Olympic site",
            "author": "AP",
            "description": "U.K. police managed to smuggle a fake bomb into Olympic Park in a security test, overshadowing a special Cabinet meeting on Monday at the park that marked 200 days until the Summer Games begin."
        }
    ]
}
```


## Authors

* **Sugandh Chaudhary**

## Acknowledgments

* Hat tip to anyone whose code was used

