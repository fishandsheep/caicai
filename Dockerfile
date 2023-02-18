FROM ubuntu:latest

WORKDIR /app

ENV OPENAI_KEY="replace_key"

COPY target/caicai-1.0.0-SNAPSHOT-runner /app

RUN chmod +x /app/caicai-1.0.0-SNAPSHOT-runner

EXPOSE 8080

CMD ["/app/caicai-1.0.0-SNAPSHOT-runner"]
