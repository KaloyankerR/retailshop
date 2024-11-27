// src/components/ProductForm.tsx

import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Product } from '../types/Product';
import { useNavigate, useParams } from 'react-router-dom';

const ProductForm: React.FC = () => {
  const [product, setProduct] = useState<Partial<Product>>({});
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    if (id) {
      fetchProduct(id);
    }
  }, [id]);

  const fetchProduct = async (id: string) => {
    try {
      const response = await api.get<Product>(`/api/products/${id}`);
      setProduct(response.data);
    } catch (error) {
      console.error('Error fetching product:', error);
    }
  };

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setProduct((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      if (id) {
        await api.put(`/api/products/${id}`, product);
      } else {
        await api.post('/api/products', product);
      }
      navigate('/');
    } catch (error) {
      console.error('Error saving product:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-4">{id ? 'Edit' : 'Add'} Product</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="name" className="block text-sm font-medium">
            Product Name
          </label>
          <input
            name="name"
            value={product.name || ''}
            onChange={handleChange}
            placeholder="Product Name"
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="description" className="block text-sm font-medium">
            Description
          </label>
          <textarea
            name="description"
            value={product.description || ''}
            onChange={handleChange}
            placeholder="Description"
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          ></textarea>
        </div>
        <div>
          <label htmlFor="price" className="block text-sm font-medium">
            Price
          </label>
          <input
            name="price"
            type="number"
            step="0.01"
            value={product.price || ''}
            onChange={handleChange}
            placeholder="Price"
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

export default ProductForm;
