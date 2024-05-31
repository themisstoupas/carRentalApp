# carRentalApp



**Car Rental Application**

The Car Rental Application is a Java-based desktop application developed to manage car rental services efficiently. It offers a user-friendly interface for both customers and car rental administrators.

**Features:**

- **Add New Cars:** 
  - Add new cars to the database with details such as model, category, daily cost, cubic capacity, and seating capacity.

- **Add New Customers:** 
  - Register new customers with their personal details, including first name, last name, gender, home address, email, and phone number.
  - The application prevents duplicate entries for customers with the same email address.

- **View Rented Cars:** 
  - View a list of currently rented cars along with details about the customers who rented them.

- **Rent a Car:** 
  - Rent a car from the available inventory by selecting the desired car model, specifying the rental duration (up to 30 days), and choosing the customer renting the car.
  - The application ensures that only available cars can be rented.

- **Search Customers and Cars:** 
  - Search for customers by their first name or last name to view their rental history, including details of the cars they have rented.
  - Search for cars by model to see which customers have rented them.

- **Delete Customers and Cars:** 
  - Remove customers and cars from the database.
  - Deleting a customer removes all associated rental history, and deleting a car removes all records of its rentals.

**Technologies Used:**

- Java
- MySQL
- Swing for GUI
- JDBC for database connectivity


