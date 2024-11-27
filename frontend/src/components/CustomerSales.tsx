import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../api/axiosConfig';

interface SaleItem {
  saleItemId: number;
  saleId: number;
  productId: number;
  quantity: number;
  unitPrice: number;
  totalPrice: number;
  productName: string;
}

interface Sale {
  saleId: number;
  saleDate: string;
  totalAmount: number;
  items: SaleItem[];
}

const CustomerSales: React.FC = () => {
  const [sales, setSales] = useState<Sale[]>([]);
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    if (id) {
      fetchSales(id);
    }
  }, [id]);

  const fetchSales = async (customerId: string) => {
    const response = await api.get<Sale[]>(`/api/sales`);
    setSales(response.data);
  };

  const totalSalesAmount = sales.reduce((total, sale) => total + sale.totalAmount, 0);

  return (
    <div>
      <h1>Sales for Customer {id}</h1>
      <h2>Total Sales Amount: ${totalSalesAmount.toFixed(2)}</h2>
      {sales.map(sale => (
        <div key={sale.saleId}>
          <h3>Sale Id: {sale.saleId} - Date: {new Date(sale.saleDate).toLocaleDateString()}</h3>
          <ul>
            {sale.items.map(item => (
              <li key={item.saleItemId}>
                {item.productName} - Quantity: {item.quantity} - Total: ${item.totalPrice.toFixed(2)}
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default CustomerSales;
