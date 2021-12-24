#!/bin/sh
mvn clean verify -P integration-test4
rc=$?
if [ $rc -ne 0 ] ; then
  echo Errors found during mvn clean verify, exit code [$rc];
  docker-compose down
fi