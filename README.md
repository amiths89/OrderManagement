# OrderManagement

This is an Order Management project with 2 separate services Order-Service and Inventory service. To run the applications you would need to install and run Apache Kafka. The order service communicates with inventory management using kafka to reserve an order when a request is sent from the order-service. Once you have Apache Kafka running, configure the ports in both projects to align with the port used by kafka.
