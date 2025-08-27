# Use the official Jenkins agent image as the base
FROM jenkins/inbound-agent:latest-jdk21

# Switch to root user
USER root

# Install Docker
RUN apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get update --allow-releaseinfo-change && \
    apt-get install -y --no-install-recommends docker.io && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Switch to normal user
USER jenkins