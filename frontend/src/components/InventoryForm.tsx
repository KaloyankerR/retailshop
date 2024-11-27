// src/components/InventoryForm.tsx

import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Inventory } from '../types/Inventory';
import { Product } from '../types/Product';
import { Shop } from '../types/Shop';
import { useNavigate, useParams } from 'react-router-dom';

const InventoryForm: React.FC = () => {
  const [inventoryItem, setInventoryItem] = useState<Partial<Inventory>>({});
  const [products, setProducts] = useState<Product[]>([]);
  const [shops, setShops] = useState<Shop[]>([]);
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    fetchProducts();
    fetchShops();
    if (id) {
      fetchInventoryItem(id);
    }
  }, [id]);

  const fetchProducts = async () => {
    try {
      const response = await api.get<Product[]>('/api/products');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  const fetchShops = async () => {
    try {
      const response = await api.get<Shop[]>('/api/shops');
      setShops(response.data);
    } catch (error) {
      console.error('Error fetching shops:', error);
    }
  };

  const fetchInventoryItem = async (id: string) => {
    try {
      const response = await api.get<Inventory>(`/api/inventory/${id}`);
      setInventoryItem(response.data);
    } catch (error) {
      console.error('Error fetching inventory item:', error);
    }
  };

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setInventoryItem((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    console.log('Inventory Item:', inventoryItem);
    console.log('ID:', id);

    try {
      if (id) {
        await api.put(`/api/inventory/${id}`, inventoryItem);
      } else {
        await api.post('/api/inventory', inventoryItem);
      }
      navigate('/');
    } catch (error) {
      console.error('Error saving inventory item:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-4">{id ? 'Edit' : 'Add'} Inventory Item</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="productId" className="block text-sm font-medium">
            Product
          </label>
          <select
            name="productId"
            value={inventoryItem.productId || ''}
            onChange={handleChange}
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">Select Product</option>
            {products.map((product) => (
              <option key={product.productId} value={product.productId}>
                {product.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="shopId" className="block text-sm font-medium">
            Shop
          </label>
          <select
            name="shopId"
            value={inventoryItem.shopId || ''}
            onChange={handleChange}
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">Select Shop</option>
            {shops.map((shop) => (
              <option key={shop.shopId} value={shop.shopId}>
                {shop.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="quantity" className="block text-sm font-medium">
            Quantity
          </label>
          <input
            name="quantity"
            type="number"
            value={inventoryItem.quantity || ''}
            onChange={handleChange}
            placeholder="Quantity"
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

export default InventoryForm;
