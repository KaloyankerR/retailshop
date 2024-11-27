import React, { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import { Sale } from "../types/Sale";
import { Link } from "react-router-dom";

const SaleList: React.FC = () => {
  const [sales, setSales] = useState<Sale[]>([]);

  useEffect(() => {
    fetchSales();
  }, []);

  const fetchSales = async () => {
    try {
      const response = await api.get<Sale[]>("/api/sales");
      setSales(response.data);
    } catch (error) {
      console.error("Error fetching sales:", error);
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Sales</h1>
      <Link
        to="/sales/add"
        className="inline-block bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mb-4"
      >
        Add Sale
      </Link>
      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Sale ID</th>
            <th className="py-2 px-4 border-b">Total Amount</th>
          </tr>
        </thead>
        <tbody>
          {sales.map((sale) => (
            <tr key={sale.saleId}>
              <td className="py-2 px-4 border-b">Sale #{sale.saleId}</td>          
              <td className="py-2 px-4 border-b">${sale.totalAmount.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SaleList;
