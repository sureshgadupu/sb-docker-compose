call mvn clean
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b

call mvn verify -P integration-test4
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" (
  call docker-compose down
 ) ELSE (
       ECHO "No errors found"
   )
exit /b



