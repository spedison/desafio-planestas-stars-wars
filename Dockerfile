FROM alpine:3.15.0

RUN apk add openjdk11-jdk openjdk11  && rm -rf /var/cache/apk/*

COPY ./target/planetas-star-wars-0.0.1-SNAPSHOT.jar /usr/src/myapp/app.jar

# Somente para explicitar as variáveis de ambiente usadas.(como exemplo)
#ENV HOST=192.168.15.22
#ENV BANCO=desafio_planetas_star_wars
#ENV USUARIO=curso_udemy
#ENV SENHA=123456

WORKDIR /usr/src/myapp
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]