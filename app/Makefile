run-dist: clean build test checkstyle report
	build\\install\\app\\bin\\app

checkstyle:
	gradlew.bat checkstyleMain

clean:
	gradlew.bat clean

build:
	gradlew.bat installDist

test:
	gradlew.bat test

report:
	gradlew.bat jacocoTestReport

.PHONY: build