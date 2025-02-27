# OrderManagement

This is an Order Management project with 2 separate services Order-Service and Inventory service. To run the applications you would need to install and run Apache Kafka. The order service communicates with inventory management using kafka to reserve an item in the inventory when a request is sent from the order-service. Once you have Apache Kafka running, configure the ports in both projects to align with the port used by kafka.

Install and setup kafka
1. Download kafka from here https://kafka.apache.org/downloads
2. Read the documentation here to set up and run kafka https://kafka.apache.org/documentation/#quickstart
3. Once Kafka is up and running edit the port used by kafka (server.port) in both Order-Service and Inventory-Service to whichever port kafka would be running.
4. Start both order-service and inventory-service
5. Use a postman to post request in order-service. Below is a sample request body

  ```json
   {
    "customerName": "John Doe",
    "product": "Laptop",
    "quantity": 2
   }
