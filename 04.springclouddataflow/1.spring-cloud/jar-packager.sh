#!/bin/bash

echo "Packaging config-server..."
cd ./config-server
mvn clean package
cd ..

echo "Packaging naming-server..."
cd ./naming-server
mvn clean package
cd ..

echo "Packaging gateway-server..."
cd ./gateway-server
mvn clean package
cd ..

echo "Packaging products-service..."
cd ./products-service
mvn clean package
cd ..

echo "Packaging orders-service..."
cd ./orders-service
mvn clean package
cd ..

echo "Packaging oauth2..."
cd ./oauth2
mvn clean package
cd ..