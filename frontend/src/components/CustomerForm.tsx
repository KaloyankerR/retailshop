import React, { useState, useEffect } from "react";
import { Customer } from "../types/Customer";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/axiosConfig";

const CustomerForm: React.FC = () => {
  const [customer, setCustomer] = useState<Partial<Customer>>({});
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    if (id) {
      fetchCustomer(id);
    }
  }, [id]);

  const fetchCustomer = async (id: string) => {
    const response = await api.get<Customer>(`/api/customers/${id}`);
    setCustomer(response.data);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      await api.put(`/api/customers/${id}`, customer);
    } else {
      await api.post("/api/customers", customer);
    }
    navigate("/");
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">
        {id ? "Edit" : "Add"} Customer
      </h1>
      <form onSubmit={handleSubmit} className="space-y-4 max-w-lg">
        <div>
          <label htmlFor="firstName" className="block text-sm font-medium">
            First Name
          </label>
          <input
            name="firstName"
            value={customer.firstName || ""}
            onChange={handleChange}
            placeholder="First Name"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="lastName" className="block text-sm font-medium">
            Last Name
          </label>
          <input
            name="lastName"
            value={customer.lastName || ""}
            onChange={handleChange}
            placeholder="Last Name"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="email" className="block text-sm font-medium">
            Email
          </label>
          <input
            name="email"
            type="email"
            value={customer.email || ""}
            onChange={handleChange}
            placeholder="Email"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="phoneNumber" className="block text-sm font-medium">
            Phone Number
          </label>
          <input
            name="phoneNumber"
            value={customer.phoneNumber || ""}
            onChange={handleChange}
            placeholder="Phone Number"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="address" className="block text-sm font-medium">
            Address
          </label>
          <input
            name="address"
            value={customer.address || ""}
            onChange={handleChange}
            placeholder="Address"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
        >
          Save
        </button>
      </form>
    </div>
  );
};

export default CustomerForm;
