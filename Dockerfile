# Java 17을 사용하는 가벼운 환경을 가져옴.
FROM eclipse-temurin:17-jdk-alpine

# 스프링부트 프로젝트가 빌드된 jar 파일의 위치를 변수로 설정.
ARG JAR_FILE=build/libs/*.jar

# 그 jar 파일을 도커 컨테이너 안으로 복사하고 이름을 app.jar로 변경.
COPY ${JAR_FILE} app.jar

# 도커 컨테이너가 켜질 때 실행할 명령어. (운영 프로필로 실행)
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]