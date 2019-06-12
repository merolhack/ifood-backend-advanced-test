# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success, it must be prepared to be fault tolerant, responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to, and briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Also, make it easy to deploy/run your service(s) locally (consider using some container/vm solution for this). Once done, share your code with us.

## Local steps

1.- Install maven dependencies:

```bash
mvn clean install
```

2.- Iniciar spring boot:

```bash
mvn spring-boot:run
```

## Heroku steps

1.- Login into the Heroku account an create an app:
```bash
heroku login
heroku create
```

2.- Create and commit the *Procfile*:

```bash
echo "java.runtime.version=1.8" > system.properties
git add system.properties && git commit -m ":wrench: chore: Java 8"
```

3.- Set the JVM build pack and deploy the war:

```bash
heroku buildpacks:set heroku/jvm
mvn clean heroku:deploy-war
```

## Spotify Links

* [Authorization Guide](https://developer.spotify.com/documentation/general/guides/authorization-guide/)
* [Get Recommendations Based on Seeds](https://developer.spotify.com/documentation/web-api/reference/browse/get-recommendations/)

## CentOs links

* [How To Install Apache Maven On CentOS 7](https://phoenixnap.com/kb/how-to-install-apache-maven-on-centos-7)
* [How to install Apache Maven on CentOS 7](https://linuxize.com/post/how-to-install-apache-maven-on-centos-7/)

## Heroku links

* [Heroku Java Support](https://devcenter.heroku.com/articles/java-support)
* [Deploying Java Applications with the Heroku Maven Plugin](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)
* [Failed to deploy spring boot app on heroku](https://stackoverflow.com/a/46027507/1006079)
* [Failed to deploy to Heroku using Maven: Forbidden](https://stackoverflow.com/q/32190254/1006079)
* [Buildpacks](https://devcenter.heroku.com/articles/buildpacks#setting-a-buildpack-on-an-application)
