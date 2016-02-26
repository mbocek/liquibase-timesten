# liquibase-timesten

[![Build Status](https://travis-ci.org/mbocek/liquibase-timesten.svg?branch=master)](https://travis-ci.org/mbocek/liquibase-timesten)
<a href="https://scan.coverity.com/projects/mbocek-liquibase-timesten">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/8077/badge.svg"/>
</a>

* run demo: ```mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:update```
* clean checksums: ```mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:clearCheckSums```
* rollback last change: ```mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:rollback -Dliquibase.rollbackCount=1```
* rollback tag: ```mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:rollback -Dliquibase.rollbackTag=1.0```
* generate documentation: ```mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:dbDoc```


