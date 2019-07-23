FROM gradle:5.5.1-jdk8

COPY . /usr/src/server

WORKDIR /usr/src/server

EXPOSE 8080

RUN "./gradlew"

CMD ["gradle", "/usr/local/bin/gradle"]
