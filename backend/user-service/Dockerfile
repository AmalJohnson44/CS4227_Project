
# Build (memory-light)
FROM gradle:8-jdk17 AS build
WORKDIR /app
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx512m -Dorg.gradle.workers.max=1 -Dorg.gradle.parallel=false"
ENV JAVA_TOOL_OPTIONS="-Xmx512m -XX:MaxMetaspaceSize=256m"
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle --no-daemon clean bootJar

# Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/app/app.jar"]
