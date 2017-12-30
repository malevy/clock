# clock
A sample app for publishing temporal events

the service uses spring-cloud-stream to connect to RabbitMQ. the current configuration
will create an exchange named *temporalEvents* and publish a message using the 
routing key of the same value (ie *temporalEvents*). the service also uses 
spring-integration, along with Hazelcast, to support leader election so that cluster
of services can be started but only one will have responsibility publishing events. all 
of the nodes will create a connection to RabbitMQ but only one will publish the actual 
message.

### running locally
the existing configuration assumes that you have an instance of RabbitMQ running locally
and using the default port of 5672. 

running a single instance of the service is as easy as 
`mvn spring-boot:run`. this will start a single node cluster and connect to RabbitMQ as 
you would expect.

if, however, you'd like to start a multi-node cluster while still running locally, do
the following:

1. `mvn clean package` will create a jar in the target folder
2. start the first node with `java -Dspring.profiles.active=local -jar target/clock-0.0.1-SNAPSHOT.jar`
3. open another terminal window and start the second instance with the same command but 
change the service port to something other than 8080. for example: 
`java -Dspring.profiles.active=local -Dserver.port=8081 -jar target/clock-0.0.1-SNAPSHOT.jar` 

you should see log information from Hazelcast that indicates a named *cloudCluster* 
has been created and it contains two members. the first instance that you started 
should be the leader and the one that is publishing the events. If you kill that instance,
leadership should transfer to the remaining node and message publishing continues.

### running in the cloud - cloud foundry
I have also included a configuration profile, cloud, and a manifest file for publishing
the service to cloud foundry. Both the configuration and manifest file assume that the target
space contains an instance of the CloudAMQP service (ie. RabbitMQ) named 'rabbitmq'.

