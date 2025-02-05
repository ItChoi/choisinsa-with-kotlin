FROM gradle:8.11.1-jdk17-corretto as build
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-admin-kt
WORKDIR /${APP_HOME}

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew :module-app-admin-kt:clean :module-app-admin-kt:build

FROM amazoncorretto:17
ENV APP_HOME=choisinsa-with-kotlin
ENV APP_NAME=module-app-admin-kt
WORKDIR /${APP_HOME}

COPY --from=build /${APP_HOME}/${APP_NAME}/build/libs/*.jar ./${APP_NAME}.jar

EXPOSE 8081
ENV PROFILE "admin"
CMD ["java", "-jar", "module-app-admin-kt.jar"]