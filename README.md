Submission for Waypoints Challenge

`make run` will make and run the application against waypoints.json.

`make test` will make and run the unit tests.

To use the application set your class path and run the Main class like so,

    export CLASSPATH=libs/json-simple-1.1.1.jar:libs/simplelatlng-1.3.1.jar:classes
    java Main <waypoints.json>

libs contains three libraries:

* json-simple-1.1.1.jar https://github.com/fangyidong/json-simple
* simplelatlng-1.3.1.jar https://github.com/JavadocMD/simplelatlng/
* junit-4.10.jar http://junit.org/junit4/

Adam Bates
