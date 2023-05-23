@REM runs the main class of the project
@echo off

set WRAPPER_PATH=./.mvn/wrapper
set PATH=%WRAPPER_PATH%;%PATH%

if not exist "%WRAPPER_PATH%\maven-wrapper.jar" (
    echo Maven wrapper not found. Please make sure the Maven wrapper is set up in your project.
    exit /b
)

mvnw exec:java -Dexec.mainClass=com.recipeFinder.Main