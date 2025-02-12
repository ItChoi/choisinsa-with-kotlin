FROM gradle:8.11.1-jdk17-corretto as build
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-choisinsa-kt
WORKDIR /${APP_HOME}

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./

RUN ./gradlew dependencies

COPY . .

RUN ./gradlew :${APP_NAME}:clean :${APP_NAME}:build

FROM amazoncorretto:17
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-choisinsa-kt
WORKDIR /${APP_HOME}

COPY --from=build /${APP_HOME}/${APP_NAME}/build/libs/*.jar ./${APP_NAME}.jar

EXPOSE 8080
ENV PROFILE "client"
#CMD ["sh", "-c", "java -jar ${APP_NAME}.jar"]

# Remote Jvm Debug -> CMD 주석 후 아래 주석 사용
EXPOSE 5005
CMD ["sh", "-c", "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar ${APP_NAME}.jar"]