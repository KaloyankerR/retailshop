import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { Customer } from '../types/Customer';
import { Sale } from '../types/Sale';
import api from '../api/axiosConfig';

const CustomerSales: React.FC = () => {
  const { customerId: id } = useParams<{ customerId: string }>();
  const [customer, setCustomer] = useState<Customer | null>(null);
  const [sales, setSales] = useState<Sale[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log(id);
    if (id) {
      fetchCustomerWithSales(Number(id));
    }
  }, [id]);

  const fetchCustomerWithSales = async (id: number) => {
    try {
      const response = await api.get<Customer>(`/api/customers/${id}`);      
      setCustomer(response.data);
      setSales(response.data.sales || []);
    } catch (error) {
      console.error('Failed to fetch customer or sales:', error);
      setCustomer(null);
      setSales([]);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!customer) {
    return <div>Customer not found or no sales available.</div>;
  }

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">
        Sales for {customer.firstName} {customer.lastName}
      </h1>
      <Link
        to="/"
        className="inline-block bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mb-4"
      >
        Back to Customers
      </Link>
      {sales.length > 0 ? (
        <table className="min-w-full bg-white border">
          <thead>
            <tr>
              <th className="py-2 px-4 border-b">Sale Date</th>
              <th className="py-2 px-4 border-b">Total Amount</th>
            </tr>
          </thead>
          <tbody>
            {sales.map(sale => (
              <tr key={sale.saleId}>
                <td className="py-2 px-4 border-b">
                  {new Date(sale.saleDate).toLocaleDateString()}
                </td>
                <td className="py-2 px-4 border-b">${sale.totalAmount.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <div>No sales found for this customer.</div>
      )}
    </div>
  );
};

export default CustomerSales;
