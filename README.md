# Retail Shop Management System

## Overview

This project is a Retail Shop Management System designed to track customers, products, shops, inventory, and sales. 

The development process included multiple phases:
1. **Design Phase**
2. **Database Design and Implementation**
3. **Backend Development**
4. **Frontend Development**

The project source code is hosted on GitHub, and you can access it here: [Retail Shop Repository](https://github.com/KaloyankerR/retailshop).  
Both the frontend and backend, along with supporting files (SQL scripts and design decisions), are included in the repository.

- **Backend**: Located in the `src` folder.
- **Frontend**: Located under the `frontend` folder.
- **Pipeline**: Includes build, test, and test report generation.

### Database Management
I used **Flyway** for managing database migrations. It creates the tables and inserts mock data automatically during application startup.

---

## How to Run the Program

### Backend (Port: 8080)

1. Clone the repository and open the project in an IDE like IntelliJ.
2. Download all required dependencies (Maven or Gradle should handle this automatically).
3. Run the `RetailShopApplication` class to start the backend server.

### Frontend (Port: 3000)

1. Navigate to the `frontend` folder:
   ```bash
   cd frontend
2. Install the necessary dependencies:
   ```bash
   npm install
3. Start the React app:
   ```bash
   npm start


## Additional Configuration

### CORS Configuration

To connect the frontend with the backend, I implemented **CORS (Cross-Origin Resource Sharing)** in the backend. You can find the configuration in the `WebConfig` file.

---

## Need Help?

If you encounter any issues running the program, feel free to contact me at:

- **Email**: [kulovkaloyan@gmail.com](mailto:kulovkaloyan@gmail.com)
