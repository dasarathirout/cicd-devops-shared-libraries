#docker build . -f CICD-DEVOPS-v1.0.Dockerfile -t dasarathirout/cicd-devops:1.0
FROM alpine:3.17.3
RUN uname -a
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

ENV HOME /root
ARG USER_HOME_DIR="/root"

RUN apk update
RUN apk add --upgrade apk-tools
RUN apk upgrade --available
RUN sync

RUN apk add openssl
RUN apk add helm
RUN apk add curl
RUN apk add openjdk17
RUN rm -rf /var/cache/apk/*
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk

RUN mkdir -p /usr/share/maven /usr/share/gradle

ARG MAVEN_URL=https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.zip
ARG GRADLE_URL=https://services.gradle.org/distributions/gradle-8.1.1-bin.zip

RUN curl -fsSL -o /tmp/maven.zip ${MAVEN_URL}
RUN unzip /tmp/maven.zip -d /usr/share/maven
RUN rm -f /tmp/maven.zip
RUN ln -s /usr/share/maven/apache-maven-3.9.1/bin /usr/bin/mvn
ENV MAVEN_HOME=/usr/share/maven/apache-maven-3.9.1

RUN curl -fsSL -o /tmp/gradle.zip ${GRADLE_URL}
RUN unzip /tmp/gradle.zip -d /usr/share/gradle
RUN rm -f /tmp/gradle.zip
RUN ln -s /usr/share/gradle/gradle-8.1.1/bin/gradle /usr/bin/gradle
ENV GRADLE_HOME=/usr/share/gradle/gradle-8.1.1

RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.27.0/bin/linux/amd64/kubectl
RUN chmod +x ./kubectl
RUN mv ./kubectl /usr/local/bin/kubectl

RUN curl -fsSL -o jq https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64
RUN chmod +x ./jq
RUN mv ./jq /usr/local/bin/jq

ENV PATH=${PATH}:${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${GRADLE_HOME}\bin

RUN java --version
RUN mvn --version
RUN gradle --version

CMD ["/bin/sh"]

#docker run -it dasarathirout/cicd-devops:1.0 /bin/sh