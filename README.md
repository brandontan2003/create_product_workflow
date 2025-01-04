# Create Product Workflow

## Description
This repository demonstrates how to use Business Process Model Notation (BPMN) to orchestrate RESTful API calls and create records in a database. The process automates the flow of business logic by invoking external APIs based on defined workflows and business rules, resulting in efficient data handling and management.

## Pre-requisites
Before you begin, ensure that you have the following tools and services installed:
1. **Java**  
   Ensure that you have **Java 8+** installed on your system. You can verify this by running:
   ```bash
   java -version
   ```
2. **Maven** is required for building and managing the project dependencies. Install it if you haven't already. Verify the installation with:
   ```bash
   mvn -version
   ```
3. **Docker** is used for containerizing the application and managing services through Docker Compose. You can check if Docker is installed with:
   ```bash
   docker --version
   ```
4. **Product Management Service**  
   This microservice is required for the BPMN workflow to make API calls. You can find the repository for the Product Management Service [here](https://github.com/brandontan2003/product_management_service).
5. **Redhat Registry Account**
   1. Register for an account at Redhat (https://registry.redhat.io/)
   2. Login into registry.redhat.io using git terminal, enter username and password
      ```text
      docker login registry.redhat.io
      ```
   3. Business Central and Kie Server login credentials
      ```text
      Username: adminUser
      Password: admin1!
      ```
   4. Kie Server API Documentation: http://localhost:9001/docs/
---

## Technologies Used
- **Java**: A versatile, platform-independent programming language used for developing the backend logic and integrating the BPMN process. Java is utilized for its rich ecosystem, stability, and performance.
- **Maven**: A build automation tool primarily used for managing project dependencies, building the application, and ensuring all required libraries and modules are available.
- **Docker**: A containerization platform used to package the application and its dependencies into a portable container. This allows for consistent development, testing, and production environments.
- **BPMN (Business Process Model and Notation)**: A graphical representation for specifying business processes in a workflow. In this project, BPMN is used to define and orchestrate the execution flow that interacts with RESTful APIs and updates the database.
---

## Setup Instructions
1. Link Gradle project
    1. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
    2. Import it as a Maven project using the pom.xml file.
2. Build the Project
    Run the following command to build and verify the project:
    ```bash
    mvn clean install
    ```
3. Start up ``kie-server`` on Docker using terminal
   ```bash
   docker compose up -d kie-server
   ```
---

## Useful API Endpoints

### Deploy Business Process
**PUT** `http://localhost:9001/services/rest/server/containers/create_product_workflow`

#### Request Body
```json
{
  "container-id": "create_product_workflow",
  "release-id": {
    "group-id": "com.example",
    "artifact-id": "create_product_workflow",
    "version": "1.0.0-SNAPSHOT"
  }
}
```

### Update Business Process Deployment
**POST** `http://localhost:9001/services/rest/server/containers/create_product_workflow/release-id`

#### Request Body
```json
{
  "group-id": "com.example",
  "artifact-id": "create_product_workflow",
  "version": "1.0.0-SNAPSHOT"
}
```

### Start New Process
**POST** `http://localhost:9001/services/rest/server/containers/create_product_workflow/processes/create_product_workflow.workflow/instances`

#### Request Body
```json
{
  "createProductUrl": "http://product-management-service:8080/api/product/v1/create",
  "createProductRequest": {
    "productName": "The Lord of the Rings",
    "productDesc": "Some Desc",
    "price": 12.00
  }
}
```

#### Response Body
It returns the processInstanceId which is needed to view the image of the business process used in [this API](#get-process-instance-image).
```text
3
```

### Get Process Instance Image
To get the processInstanceId, call the [following API](#start-new-process). <br>
**GET** `http://localhost:9001/services/rest/server/containers/create_product_workflow/images/processes/instances/<processInstanceId>`
