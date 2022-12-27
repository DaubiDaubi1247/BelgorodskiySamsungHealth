FROM openjdk:11

RUN apt-get update
RUN apt-get install nano

RUN mkdir "jarHolder"
ADD /target/demo1-0.0.1-SNAPSHOT.jar /jarHolder/backend.jar
RUN mkdir "json"
RUN mkdir "pdf"

RUN mkdir "scripts"
ADD /scripts/s1.py scripts/s1.py
RUN sed -i -e 's/\r$//' scripts/s1.py
RUN cd scripts
RUN apt-get update
RUN apt-get install -y python3-pip
RUN pip install --upgrade pip
RUN pip install yadisk

RUN cd ..
RUN apt-get update
RUN apt-get -y install cron

ENTRYPOINT ["java", "-jar", "/jarHolder/backend.jar"]

RUN crontab -l | { cat; echo "* * * * *  ./scripts/s1.py"; } | crontab -
CMD cron