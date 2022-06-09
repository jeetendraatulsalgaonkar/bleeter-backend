FROM adoptopenjdk/openjdk15
# maintainer
MAINTAINER "Jeetendra Salgaonkar <jeetendra.salgaonkar@gmail.com>"
EXPOSE 8091
COPY . /src
WORKDIR /src
RUN ./gradlew clean build

RUN ls build/

COPY --from=build /build/libs/bleeter-backend-1.0.0-SNAPSHOT.jar /bin/runner/run.jar
WORKDIR /bin/runner

CMD ["java","-jar","run.jar"]