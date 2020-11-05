FROM java:8-jre

MAINTAINER trongtb1988@gmail.com

VOLUME [ "/data" ]

WORKDIR /data

EXPOSE 8080
ENTRYPOINT [ "java" ]
CMD ["-?"]