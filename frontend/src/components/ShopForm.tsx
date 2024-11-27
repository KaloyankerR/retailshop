import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Shop } from '../types/Shop';
import { useNavigate, useParams } from 'react-router-dom';

const ShopForm: React.FC = () => {
  const [shop, setShop] = useState<Partial<Shop>>({});
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    if (id) {
      fetchShop(id);
    }
  }, [id]);

  const fetchShop = async (id: string) => {
    try {
      const response = await api.get<Shop>(`/api/shops/${id}`);
      setShop(response.data);
    } catch (error) {
      console.error('Error fetching shop:', error);
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setShop((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (id) {
        await api.put(`/api/shops/${id}`, shop);
      } else {
        await api.post('/api/shops', shop);
      }
      navigate('/');
    } catch (error) {
      console.error('Error saving shop:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-4">{id ? 'Edit' : 'Add'} Shop</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="name" className="block text-sm font-medium">
            Shop Name
          </label>
          <input
            name="name"
            value={shop.name || ''}
            onChange={handleChange}
            placeholder="Shop Name"
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
            value={shop.address || ''}
            onChange={handleChange}
            placeholder="Address"
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="phoneNumber" className="block text-sm font-medium">
            Phone Number
          </label>
          <input
            name="phoneNumber"
            value={shop.phoneNumber || ''}
            onChange={handleChange}
            placeholder="Phone Number"
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

export default ShopForm;
