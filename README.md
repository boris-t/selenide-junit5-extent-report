# Selenide Junit5  Extent Report

Simple google test, using Selenide, gradle, junit5, generating ExtentReports

## How to use:


*  clone project: `git clone https://github.com/boris-t/selenide-junit5-extent-report.git`
*  go to project's dir
*  run command `./gradlew test`
*  see reports in **/test-output folder** (can be changed)

`@DisplayName` - test step name

**BaseTestClass** - main test class containing configuration for tests and reports

Every test class should have extension: `@ExtendWith(BaseTestClass.class)`

## Documentation:

[ExtentReports](http://extentreports.com/docs/versions/3/java/)

[Junit5](https://junit.org/junit5/docs/current/user-guide/)

[Selenide](https://selenide.org/documentation.html)

[Gradle](https://docs.gradle.org/current/userguide/userguide.html)
