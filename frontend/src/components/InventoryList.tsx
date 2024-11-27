import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import { Inventory } from '../types/Inventory';
import { Link } from 'react-router-dom';

const InventoryList: React.FC = () => {
  const [inventoryItems, setInventoryItems] = useState<Inventory[]>([]);

  useEffect(() => {
    fetchInventory();
  }, []);

  const fetchInventory = async () => {
    try {
      const response = await api.get<Inventory[]>('/api/inventory');
      setInventoryItems(response.data);
    } catch (error) {
      console.error('Error fetching inventory:', error);
    }
  };

  const deleteInventoryItem = async (id: number) => {
    try {
      await api.delete(`/api/inventory/${id}`);
      setInventoryItems(inventoryItems.filter((item) => item.inventoryId !== id));
    } catch (error) {
      console.error('Error deleting inventory item:', error);
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Inventory</h1>
      <Link
        to="/inventory/add"
        className="inline-block bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mb-4"
      >
        Add Inventory Item
      </Link>
      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Product</th>
            <th className="py-2 px-4 border-b">Shop</th>
            <th className="py-2 px-4 border-b">Quantity</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {inventoryItems.map((item) => (
            <tr key={item.inventoryId}>
              <td className="py-2 px-4 border-b">{item.productName}</td>
              <td className="py-2 px-4 border-b">{item.shopName}</td>
              <td className="py-2 px-4 border-b">{item.quantity}</td>
              <td className="py-2 px-4 border-b">
                <Link
                  to={`/inventory/edit/${item.inventoryId}`}
                  className="btn text-blue-500 hover:underline mr-2"
                >
                  Edit
                </Link>
                <button
                  onClick={() => deleteInventoryItem(item.inventoryId)}
                  className="btn text-red-500 hover:underline"
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

export default InventoryList;
