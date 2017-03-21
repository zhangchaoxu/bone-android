@ECHO OFF&PUSHD %~DP0 &TITLE Upload
SetLocal EnableDelayedExpansion

:Menu
echo.&echo 0  Exist
echo.&echo 1  Alipay
echo.&echo 2  MiPush
echo.&echo.
set /p a=Pls Enter:
IF NOT "%a%"=="" SET a=%a:~0,1%
if "%a%"=="0" Goto Exist
if "%a%"=="1" Goto Alipay
if "%a%"=="2" Goto MiPush
echo.&echo Unknown number！
PAUSE >NUL && CLS && GOTO Menu

:Exist
exist
CLS && ECHO.&ECHO Finish, Press Any key to back! &&PAUSE>NUL && CLS && GOTO MENU

:Alipay
gradlew :Alipay:bintrayUpload
CLS && ECHO.&ECHO Finish, Press Any key to back! &&PAUSE>NUL && CLS && GOTO MENU

:MiPush
gradlew :MiPush:bintrayUpload
CLS && ECHO.&ECHO Finish, Press Any key to back! &&PAUSE>NUL && CLS && GOTO MENU

