#!/bin/bash


GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' 

echo -e "${YELLOW}Bookmark API Docker Setup${NC}"
echo "========================================"


if ! command -v docker &> /dev/null; then
    echo -e "${RED}Docker is not installed. Please install Docker first.${NC}"
    exit 1
fi


if ! command -v docker-compose &> /dev/null; then
    echo -e "${RED}Docker Compose is not installed. Please install Docker Compose first.${NC}"
    exit 1
fi


echo -e "${YELLOW}Creating necessary directories...${NC}"
mkdir -p kafka-producer


if [ -f "kafka_producer.py" ] && [ ! -f "kafka-producer/kafka_producer.py" ]; then
    echo -e "${YELLOW}Copying Kafka producer script to kafka-producer directory...${NC}"
    cp kafka_producer.py kafka-producer/
fi


echo -e "${YELLOW}Building and starting containers...${NC}"
docker-compose up -d --build


echo -e "${YELLOW}Checking container status...${NC}"
sleep 5

if [ "$(docker-compose ps -q | wc -l)" -ge 4 ]; then
    echo -e "${GREEN}All containers are running!${NC}"
    echo -e "${GREEN}Bookmark API is available at http://localhost:8080${NC}"
    echo -e "${GREEN}MongoDB is available at localhost:27017${NC}"
    echo -e "${GREEN}Kafka is available at localhost:9092${NC}"
    
    echo -e "\n${YELLOW}To interact with the Kafka producer:${NC}"
    echo -e "docker-compose exec kafka-producer python kafka_producer.py"
    
    echo -e "\n${YELLOW}To stop the application:${NC}"
    echo -e "docker-compose down"
else
    echo -e "${RED}Some containers failed to start. Check with 'docker-compose ps'${NC}"
fi 