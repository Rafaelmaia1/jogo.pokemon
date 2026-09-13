@echo off
setlocal EnableExtensions EnableDelayedExpansion
cd /d "%~dp0"
set "JDK=C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
if not exist "%JDK%\bin\javac.exe" (
	where javac >nul 2>&1
	if errorlevel 1 (
		echo JDK nao encontrado. Instale o JDK do Java e tente novamente.
		pause
		exit /b 1
	)
	set "JAVAC=javac"
) else (
	set "JAVAC=%JDK%\bin\javac.exe"
)
if not exist bin mkdir bin
set "SOURCES="
for /r "src" %%F in (*.java) do set "SOURCES=!SOURCES! "%%F""
"%JAVAC%" -d bin %SOURCES%
if errorlevel 1 (
	echo.
	echo Nao foi possivel compilar. Instale o JDK do Java e tente novamente.
	pause
	exit /b 1
)
java -cp "bin" arenaelemental.Main
if errorlevel 1 pause