import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import { Shop } from '../types/Shop';
import { Link } from 'react-router-dom';

const ShopList: React.FC = () => {
  const [shops, setShops] = useState<Shop[]>([]);

  useEffect(() => {
    fetchShops();
  }, []);

  const fetchShops = async () => {
    try {
      const response = await api.get<Shop[]>('/api/shops');
      setShops(response.data);
    } catch (error) {
      console.error('Error fetching shops:', error);
    }
  };

  const deleteShop = async (id: number) => {
    try {
      await api.delete(`/api/shops/${id}`);
      setShops(shops.filter((shop) => shop.shopId !== id));
    } catch (error) {
      console.error('Error deleting shop:', error);
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Shops</h1>
      <Link
        to="/shops/add"
        className="inline-block bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mb-4"
      >
        Add Shop
      </Link>
      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Name</th>
            <th className="py-2 px-4 border-b">Address</th>
            <th className="py-2 px-4 border-b">Phone Number</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {shops.map((shop) => (
            <tr key={shop.shopId}>
              <td className="py-2 px-4 border-b">{shop.name}</td>
              <td className="py-2 px-4 border-b">{shop.address}</td>
              <td className="py-2 px-4 border-b">{shop.phoneNumber}</td>
              <td className="py-2 px-4 border-b">
                <Link
                  to={`/shops/edit/${shop.shopId}`}
                  className="btn text-blue-500 hover:underline mr-2"
                >
                  Edit
                </Link>
                <button
                  onClick={() => deleteShop(shop.shopId)}
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

export default ShopList;
