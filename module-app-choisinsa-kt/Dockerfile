FROM gradle:8.11.1-jdk17-corretto as build
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-choisinsa-kt
WORKDIR /${APP_HOME}

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./

RUN ./gradlew dependencies

COPY . .

RUN ./gradlew :module-app-choisinsa-kt:clean :module-app-choisinsa-kt:build

FROM amazoncorretto:17
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-choisinsa-kt
WORKDIR /${APP_HOME}

COPY --from=build /${APP_HOME}/${APP_NAME}/build/libs/*.jar ./${APP_NAME}.jar

EXPOSE 8080
ENV PROFILE "client"
#CMD ["java", "-jar", "module-app-choisinsa-kt.jar"]

# Remote Jvm Debug -> CMD 주석 후 아래 주석 사용
EXPOSE 5005
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "module-app-choisinsa-kt.jar"]