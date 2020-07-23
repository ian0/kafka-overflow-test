# Kafka retries with an overflow into a lower priority topic

Example app using Spring Kafka to retry message processing failures and then send the message to a lower priority queue.

Usage:  Set the address of the Bootstrap Server in the appropiate application.properties files.

On the kafka server create the retry topic and the overflow topic

/usr/hdp/3.1.0.0-78/kafka/bin/kafka-topics.sh --create --zookeeper localhost:5181 --replication-factor 1 --partitions 1 --topic retry-test-overflow
/usr/hdp/3.1.0.0-78/kafka/bin/kafka-topics.sh --create --zookeeper localhost:5181 --replication-factor 1 --partitions 1 --topic retry-test




The Consumer with read all incoming messages and throw an error if the message contains the word "Test", it will retry twice and then send the message to the overflow topic

Start the consumer

Start the producer

Start up the message producer:
- cd kafka-overflow-test/message-producer
- mvn spring-boot:run
- send a message to the consumer 

curl -X POST -F 'message=Test message' http://localhost:9000/kafka/publish

Run an console consumer on the overflow topic and you will see the Test message

/usr/hdp/3.1.0.0-78/kafka/bin/kafka-console-consumer.sh --bootstrap-server hdp-sandbox:9092 --topic retry-test-overflow



