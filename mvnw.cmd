@echo off
setlocal

set "MAVEN_VERSION=3.9.14"
set "DIST_NAME=apache-maven-%MAVEN_VERSION%-bin"
set "DIST_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/%DIST_NAME%.zip"
set "INSTALL_ROOT=%USERPROFILE%\.m2\wrapper\dists\%DIST_NAME%"
set "ARCHIVE_PATH=%INSTALL_ROOT%\%DIST_NAME%.zip"
set "MAVEN_HOME=%INSTALL_ROOT%\apache-maven-%MAVEN_VERSION%"
set "MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd"

if exist "%MVN_CMD%" goto run

if not exist "%INSTALL_ROOT%" mkdir "%INSTALL_ROOT%"

if not exist "%ARCHIVE_PATH%" (
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%DIST_URL%' -OutFile '%ARCHIVE_PATH%'"
  if errorlevel 1 goto download_error
)

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "Expand-Archive -LiteralPath '%ARCHIVE_PATH%' -DestinationPath '%INSTALL_ROOT%' -Force"
if errorlevel 1 goto unpack_error

:run
call "%MVN_CMD%" %*
exit /b %errorlevel%

:download_error
echo Failed to download Maven from %DIST_URL%.
exit /b 1

:unpack_error
echo Failed to unpack Maven archive at %ARCHIVE_PATH%.
exit /b 1
