FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install && \
    mkdir -p target/dependency \

WORKDIR target/dependency

RUN jar -xf ../*.jar

FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

RUN addgroup -S app && adduser -S app -G app
USER app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.lemonprogis.zarfdemo.Application"]