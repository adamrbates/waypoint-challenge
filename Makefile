CLASSPATH=libs:libs/json-simple-1.1.1.jar:libs/simplelatlng-1.3.1.jar:classes

CLASSES=src/Main.java \
		classes/Processor.class \
		classes/Parser.class \
		classes/Waypoint.class \
		classes/TimeTravelException.class \
		classes/ParseException.class
TESTCLASSES=tests/TestParser.class \
			tests/TestProcessor.class \
			tests/TestRunner.class\
			tests/TestWaypoint.class

.PHONY: run clean test

run: classes/Main.class
	java -cp $(CLASSPATH) Main waypoints.json

test: $(CLASSES) $(TESTCLASSES)
	java -cp $(CLASSPATH):tests:libs/junit-4.10.jar TestRunner

clean:
	rm classes/*.class
	rm tests/*.class

classes/Main.class: src/Main.java $(CLASSES)
	javac -d classes -cp $(CLASSPATH) src/Main.java

classes/Processor.class: src/Processor.java classes/Waypoint.class classes/TimeTravelException.class
	javac -d classes -cp $(CLASSPATH) src/Processor.java

classes/Parser.class: src/Parser.java classes/Waypoint.class classes/ParseException.class
	javac -d classes -cp $(CLASSPATH) src/Parser.java

classes/Waypoint.class: src/Waypoint.java
	javac -d classes -cp $(CLASSPATH) src/Waypoint.java

classes/ParseException.class: src/ParseException.java
	javac -d classes -cp $(CLASSPATH) src/ParseException.java

classes/TimeTravelException.class: src/TimeTravelException.java
	javac -d classes -cp $(CLASSPATH) src/TimeTravelException.java

tests/TestParser.class: tests/TestParser.java
	javac -d tests -cp $(CLASSPATH):tests:libs/junit-4.10.jar tests/TestParser.java

tests/TestProcessor.class: tests/TestProcessor.java
	javac -d tests -cp $(CLASSPATH):tests:libs/junit-4.10.jar tests/TestProcessor.java

tests/TestWaypoint.class: tests/TestWaypoint.java
	javac -d tests -cp $(CLASSPATH):tests:libs/junit-4.10.jar tests/TestWaypoint.java

tests/TestRunner.class: tests/TestRunner.java tests/TestProcessor.class tests/TestParser.class tests/TestWaypoint.class
	javac -d tests -cp $(CLASSPATH):tests:libs/junit-4.10.jar tests/TestRunner.java
	
