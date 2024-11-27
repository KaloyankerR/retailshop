// src/components/ProductList.tsx

import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import { Product } from '../types/Product';
import { Link } from 'react-router-dom';

const ProductList: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await api.get<Product[]>('/api/products');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  const deleteProduct = async (id: number) => {
    try {
      await api.delete(`/api/products/${id}`);
      setProducts(products.filter((product) => product.productId !== id));
    } catch (error) {
      console.error('Error deleting product:', error);
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Products</h1>
      <Link
        to="/products/add"
        className="inline-block bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 mb-4"
      >
        Add Product
      </Link>
      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Name</th>
            <th className="py-2 px-4 border-b">Price</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.productId}>
              <td className="py-2 px-4 border-b">{product.name}</td>
              <td className="py-2 px-4 border-b">
                ${product.price.toFixed(2)}
              </td>
              <td className="py-2 px-4 border-b">
                <Link
                  to={`/products/edit/${product.productId}`}
                  className="btn text-blue-500 hover:underline mr-2"
                >
                  Edit
                </Link>
                <button
                  onClick={() => deleteProduct(product.productId)}
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

export default ProductList;
