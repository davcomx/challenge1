# Challenge1

You’re asked to write a program in Java which determines if two cities are connected. Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

List of roads is available in a file. File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

It will be deployed as a spring-boot app and expose one endpoint:
http://localhost:8080/connected?origin=city1&destination=city2

Your program should respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2. Any unexpected input should result in a ’no’ response.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Download Eclipse + Buildship Gradle Integration 2.0 
```

## Running the tests

```
path: /src/test/java/com/dl/Challenge1ApplicationTests.java
command: Right click on Challenge1ApplicationTests.java + Run As + JUnit Test
Eclipse shortcut: Alt+Shift+X, T
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **davcomx** - *Initial work* - [davcomx](https://github.com/davcomx)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## Acknowledgments

* GOD
* IRY
* Inspiration

