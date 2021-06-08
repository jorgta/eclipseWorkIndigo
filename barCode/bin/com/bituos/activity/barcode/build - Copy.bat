rem create a variable for current directory
set PROJ_DIR=%CD%

rem directory where javac.exe is located
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_79\bin

rem directory where javac.exe is located
set JAVA_NAME=BeanSmsTest.java
set JAVA_JAR=BeanSmsTest.jar
set JAVA_CLASS_FILE=BeanSmsTest.class

pause

rem find where file android.jar is located
set ANDROID_PLATFORM=C:\Users\Public\Documents\Embarcadero\Studio\17.0\PlatformSDKs\android-sdk-windows\platforms\android-23

mkdir output\classes
set OUTPUTCLASS_DIR=%CD%\output\classes

set VERBOSE=0
if x%VERBOSE% == x1 SET VERBOSE_FLAG=-verbose

rem compile customClass.java - create customClass.class file
rem javac -source 1.6 -target 1.6 %VERBOSE_FLAG% -Xlint:deprecation -cp %ANDROID_PLATFORM%\android.jar;classes.jar -d "%OUTPUTCLASS_DIR%" "%PROJ_DIR%\%JAVA_NAME%"
"%JAVA_HOME%\javac.exe" -source 1.6 -target 1.6 %VERBOSE_FLAG% -Xlint:deprecation -cp %ANDROID_PLATFORM%\android.jar;classes.jar -d "%OUTPUTCLASS_DIR%" "%PROJ_DIR%\%JAVA_NAME%"

pause


REM Creating jar containing the new class
mkdir output\jar
set OUTPUTJAR_DIR=%CD%\output\jar
jar cvf %OUTPUTJAR_DIR%\%JAVA_JAR% -C %OUTPUTCLASS_DIR% com

pause