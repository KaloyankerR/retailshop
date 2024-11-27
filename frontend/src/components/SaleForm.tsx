import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import { Sale } from '../types/Sale';
import { Customer } from '../types/Customer';
import { Shop } from '../types/Shop';
import { Product } from '../types/Product';
import { SaleItem } from '../types/SaleItem';
import { useNavigate, useParams } from 'react-router-dom';

const SaleForm: React.FC = () => {
  const [sale, setSale] = useState<Partial<Sale>>({ items: [] });
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [shops, setShops] = useState<Shop[]>([]);
  const [products, setProducts] = useState<Product[]>([]);
  const [newItem, setNewItem] = useState<Partial<SaleItem>>({});
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    fetchCustomers();
    fetchShops();
    fetchProducts();
    if (id) {
      fetchSale(id);
    }
  }, [id]);

  const fetchCustomers = async () => {
    try {
      const response = await api.get<Customer[]>('/api/customers');
      setCustomers(response.data);
    } catch (error) {
      console.error('Error fetching customers:', error);
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

  const fetchProducts = async () => {
    try {
      const response = await api.get<Product[]>('/api/products');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  const fetchSale = async (id: string) => {
    try {
      const response = await api.get<Sale>(`/api/sales/${id}`);
      setSale(response.data);
    } catch (error) {
      console.error('Error fetching sale:', error);
    }
  };

  const handleSaleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const { name, value } = e.target;
    setSale((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleNewItemChange = (
    e: React.ChangeEvent<HTMLSelectElement | HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setNewItem((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const addItem = () => {
    if (newItem.productId && newItem.quantity && newItem.unitPrice) {
      const quantity = parseFloat(newItem.quantity.toString());
      const unitPrice = parseFloat(newItem.unitPrice.toString());

      const totalPrice = quantity * unitPrice;
      console.log('totalPrice:', totalPrice);

      const product = products.find(p => p.productId === Number(newItem.productId));
      const productName = product ? product.name : '';

      const saleItem: SaleItem = {
        ...newItem,
        quantity,
        unitPrice,
        totalPrice,
        productName,
      } as SaleItem;

      setSale((prevState) => ({
        ...prevState,
        items: [...(prevState.items || []), saleItem],
      }));
      setNewItem({});
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const totalAmount = sale.items?.reduce((sum, item) => {
        const itemTotal = item.totalPrice || 0;
        return sum + itemTotal;
      }, 0) || 0;

      const saleData = {
        ...sale,
        saleDate: new Date().toISOString(),
        totalAmount,
      };

      if (id) {
        await api.put(`/api/sales/${id}`, saleData);
      } else {
        await api.post('/api/sales', saleData);
      }
      navigate('/');
    } catch (error) {
      console.error('Error saving sale:', error);
    }
  };

  return (
    <div className="max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">{id ? 'Edit' : 'Add'} Sale</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="customerId" className="block text-sm font-medium">
            Customer
          </label>
          <select
            name="customerId"
            value={sale.customerId || ''}
            onChange={handleSaleChange}
            required
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">Select Customer</option>
            {customers.map((customer) => (
              <option key={customer.customerId} value={customer.customerId}>
                {customer.firstName} {customer.lastName}
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
            value={sale.shopId || ''}
            onChange={handleSaleChange}
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

        <h3 className="text-xl font-bold mt-6">Add Sale Item</h3>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
          <div>
            <label htmlFor="productId" className="block text-sm font-medium">
              Product
            </label>
            <select
              name="productId"
              value={newItem.productId || ''}
              onChange={handleNewItemChange}
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
            <label htmlFor="quantity" className="block text-sm font-medium">
              Quantity
            </label>
            <input
              name="quantity"
              type="number"
              value={newItem.quantity || ''}
              onChange={handleNewItemChange}
              placeholder="Quantity"
              required
              className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
          <div>
            <label htmlFor="unitPrice" className="block text-sm font-medium">
              Unit Price
            </label>
            <input
              name="unitPrice"
              type="number"
              step="0.01"
              value={newItem.unitPrice || ''}
              onChange={handleNewItemChange}
              placeholder="Unit Price"
              required
              className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
          <div className="md:col-span-3">
            <button
              type="button"
              onClick={addItem}
              className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
            >
              Add Item
            </button>
          </div>
        </div>

        <h3 className="text-xl font-bold mt-6">Sale Items</h3>
        <table className="min-w-full bg-white border">
          <thead>
            <tr>
              <th className="py-2 px-4 border-b">Product ID</th>
              <th className="py-2 px-4 border-b">Quantity</th>
              <th className="py-2 px-4 border-b">Unit Price</th>
            </tr>
          </thead>
          <tbody>
            {(sale.items || []).map((item, index) => (
              <tr key={index}>
                <td className="py-2 px-4 border-b">{item.productId}</td>
                <td className="py-2 px-4 border-b">{item.quantity}</td>
                <td className="py-2 px-4 border-b">${item.unitPrice?.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mt-4"
        >
          Save Sale
        </button>
      </form>
    </div>
  );
};

export default SaleForm;
