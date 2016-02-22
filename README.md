# liquibase-timesten


run demo: mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:update
clean checksums: mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:clearCheckSums
rollback last change: mvn clean install & mvn -pl demo clean package -Pdev,timesten liquibase:rollback -Dliquibase.rollbackCount=1


