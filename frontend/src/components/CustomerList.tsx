import React, { useEffect, useState } from "react";
import { Customer } from "../types/Customer";
import { Link } from "react-router-dom";
import api from "../api/axiosConfig";

interface CustomerListProps {
  limit?: number;
}

const CustomerList: React.FC<CustomerListProps> = ({ limit }) => {
  const [customers, setCustomers] = useState<Customer[]>([]);

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    const response = await api.get<Customer[]>("/api/customers");
    if (limit) {
      setCustomers(response.data.slice(0, limit));
    } else {
      setCustomers(response.data);
    }
  };

  const deleteCustomer = async (id: number) => {
    await api.delete(`/api/customers/${id}`);
    setCustomers(customers.filter((customer) => customer.customerId !== id));
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Customers</h1>
      <Link
        to="/add"
        className="inline-block bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mb-4"
      >
        Add Customer
      </Link>
      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Name</th>
            <th className="py-2 px-4 border-b">Email</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.map((customer) => (
            <tr key={customer.customerId}>
              <td className="py-2 px-4 border-b">
                {customer.firstName} {customer.lastName}
              </td>
              <td className="py-2 px-4 border-b">{customer.email}</td>
              <td className="py-2 px-4 border-b">
                <Link
                  to={`/customers/${customer.customerId}/sales`}
                  className="btn text-blue-500 hover:underline mr-2"
                >
                  View Sales
                </Link>
                <Link
                  to={`/edit/${customer.customerId}`}
                  className="btn text-blue-500 hover:underline mr-2"
                >
                  Edit
                </Link>
                <button
                  onClick={() => deleteCustomer(customer.customerId)}
                  className="btn text-red-500 hover:underline mr-2"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CustomerList;
